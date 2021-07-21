package com.example.composeexample.data.data_source

import com.example.composeexample.domain.result.DataSourceType

interface DataSourceProvider{
    val dataSourceType: DataSourceType
}

