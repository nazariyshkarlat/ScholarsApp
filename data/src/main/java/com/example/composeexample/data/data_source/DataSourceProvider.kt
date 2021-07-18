package com.example.composeexample.data.data_source

import com.example.composeexample.data.extensions.isNetworkAbsent
import com.example.composeexample.domain.result.DataSourceType

interface DataSourceProvider{
    val dataSourceType: DataSourceType
}

object DefDataSourceProvider : DataSourceProvider{
    override val dataSourceType: DataSourceType
        get() = run {
            return if(isNetworkAbsent) DataSourceType.NETWORK
            else DataSourceType.NETWORK
        }

}
