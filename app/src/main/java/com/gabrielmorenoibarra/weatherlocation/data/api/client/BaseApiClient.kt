package com.gabrielmorenoibarra.weatherlocation.data.api.client

import okhttp3.OkHttpClient
import retrofit2.Retrofit

abstract class BaseApiClient {

    protected var retrofit: Retrofit? = null

    abstract fun get(): Retrofit
    protected abstract fun getClient(): OkHttpClient
}
