package com.gabrielmorenoibarra.weatherlocation.data.api.client.usecase

import com.gabrielmorenoibarra.weatherlocation.App
import com.gabrielmorenoibarra.weatherlocation.BuildConfig
import com.gabrielmorenoibarra.weatherlocation.data.api.client.BaseApiClient
import com.gabrielmorenoibarra.weatherlocation.framework.project.util.addCache
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object ApiClient : BaseApiClient() {

    override fun get(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build()
        }
        return retrofit!!
    }

    override fun getClient(): OkHttpClient {
        val cacheSize = (100 * 1024 * 1024).toLong()
        val cache = Cache(App.instance.cacheDir, cacheSize)

        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
            clientBuilder
                .addInterceptor(loggingInterceptor)
//                    .addInterceptor(CurlLoggerInterceptor())
                .addInterceptor(ChuckInterceptor(App.instance))
        }

        clientBuilder
            .cache(cache)
            .addNetworkInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                requestBuilder.addHeader("Content-Type", "application/json")
                requestBuilder.addHeader("Accept-Language", Locale.getDefault().toString().replace("_", "-"))
                requestBuilder.addHeader("Operative-System", "android")
                requestBuilder.addHeader("App-Version", BuildConfig.VERSION_NAME)
                requestBuilder.addCache()
                chain.proceed(requestBuilder.build())
            }
//        clientBuilder.addInterceptor(IdTokenInterceptor(App.instance))
        return clientBuilder.build()
    }
}
