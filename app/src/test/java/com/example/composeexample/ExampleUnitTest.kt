package com.example.composeexample

import com.example.composeexample.data.use_case.get_articles.data_source.api.ArticlesApiImpl
import com.example.composeexample.data.ktor.xml.XmlFeature
import com.example.composeexample.data.ktor.xml.XmlSerializer
import com.example.composeexample.extensions.unescape
import com.example.data.BuildConfig
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun checkXmlParsing(){
        runBlocking {
            ArticlesApiImpl(
                baseUrl = BuildConfig.BASE_URL,
                client = HttpClient {
                    install(HttpTimeout) {
                        requestTimeoutMillis = 25 * 1000 // 25 seconds
                    }
                    install(XmlFeature) {
                        serializer = XmlSerializer.Default()
                    }
                    install(Logging) {
                        logger = object : Logger {
                            override fun log(message: String) {
                                println(message.unescape().toString())
                            }
                        }
                        level = LogLevel.BODY
                    }
                    install(DefaultRequest) {
                        accept(ContentType.Application.Xml)
                    }

                }
            ).getAstroPhysicsArticles().also {
                println(it)
            }
        }
    }
}