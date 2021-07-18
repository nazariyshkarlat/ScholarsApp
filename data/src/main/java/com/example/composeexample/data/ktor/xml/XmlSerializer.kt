package com.example.composeexample.data.ktor.xml

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.util.reflect.*
import io.ktor.utils.io.core.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer
import nl.adaptivity.xmlutil.serialization.XML

interface XmlSerializer {
    public fun write(data: Any, contentType: ContentType): OutgoingContent

    public fun write(data: Any): OutgoingContent = write(data, ContentType.Application.Xml)

    public fun read(type: TypeInfo, body: Input): Any

    class Default(private val xml: XML = XML{
        unknownChildHandler = { _, _, _, _ -> }
    }) : XmlSerializer {

        override fun write(data: Any, contentType: ContentType): OutgoingContent {
            val text = xml.encodeToString(buildSerializer(data, xml.serializersModule), data)
            return TextContent(text, contentType)
        }

        @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
        override fun read(type: TypeInfo, body: Input): Any {
            val text = body.readText()
            val deserializationStrategy = xml.serializersModule.getContextual(type.type)
            val mapper = deserializationStrategy ?: (type.kotlinType?.let { serializer(it) } ?: type.type.serializer())
            return xml.decodeFromString(mapper, text)!!
        }
    }
}

@Suppress("UNCHECKED_CAST")
private fun buildSerializer(value: Any, module: SerializersModule): KSerializer<Any> = when (value) {
    is JsonElement -> JsonElement.serializer()
    is List<*> -> ListSerializer(value.elementSerializer(module))
    is Array<*> -> value.firstOrNull()?.let { buildSerializer(it, module) } ?: ListSerializer(String.serializer())
    is Set<*> -> SetSerializer(value.elementSerializer(module))
    is Map<*, *> -> {
        val keySerializer = value.keys.elementSerializer(module)
        val valueSerializer = value.values.elementSerializer(module)
        MapSerializer(keySerializer, valueSerializer)
    }
    else -> {
        @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
        module.getContextual(value::class) ?: value::class.serializer()
    }
} as KSerializer<Any>

@Suppress("ReturnCount")
@OptIn(ExperimentalSerializationApi::class)
private fun Collection<*>.elementSerializer(module: SerializersModule): KSerializer<*> {
    val serializers: List<KSerializer<*>> = filterNotNull()
        .map { buildSerializer(it, module) }
        .distinctBy { it.descriptor.serialName }

    if (serializers.size > 1) {
        error(
            "Serializing collections of different element types is not yet supported. " +
                    "Selected serializers: ${serializers.map { it.descriptor.serialName }}"
        )
    }

    val selected = serializers.singleOrNull() ?: String.serializer()

    if (selected.descriptor.isNullable) {
        return selected
    }

    @Suppress("UNCHECKED_CAST")
    selected as KSerializer<Any>

    if (any { it == null }) {
        return selected.nullable
    }

    return selected
}