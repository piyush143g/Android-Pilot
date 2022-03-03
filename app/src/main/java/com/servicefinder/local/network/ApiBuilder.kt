package com.servicefinder.local.network

class ApiBuilder {
    private lateinit var apiServiceFactory: ApiServiceFactory
    companion object{
        fun initialize(apiServiceFactory: ApiServiceFactory): ApiBuilder{
            return ApiBuilder().apply {
                this.apiServiceFactory = apiServiceFactory
            }
        }
    }

    fun <T> getRetrofitService(serivceClass: Class<T>): T {
        return apiServiceFactory.buildApi(
            serivceClass
        )
    }

}