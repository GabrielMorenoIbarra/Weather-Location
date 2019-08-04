package com.gabrielmorenoibarra.weatherlocation.data.api.manager

import com.gabrielmorenoibarra.generic.util.KLog
import com.gabrielmorenoibarra.generic.util.Success
import com.gabrielmorenoibarra.weatherlocation.framework.project.util.Failure
import com.gabrielmorenoibarra.weatherlocation.framework.project.util.error.ServerError
import com.gabrielmorenoibarra.weatherlocation.framework.project.util.logError
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class ApiManager {

    interface Listener<T> {
        fun onSuccess(result: T)
        fun onFailure(serverError: ServerError? = null)
    }

    protected fun enqueue(call: Call<JsonElement>, listener: Listener<JsonElement>) {
        call.enqueue(object : Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                handleResponse(response, listener)
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                t.message?.logError()
                listener.onFailure()
            }
        })
    }

    protected fun execute(call: Call<JsonElement>, listener: Listener<JsonElement>): Response<JsonElement> {
        val response = call.execute()
        handleResponse(response, listener)
        return response
    }

    private fun handleResponse(response: Response<JsonElement>, listener: Listener<JsonElement>) {
        val code = response.code()
        if (code in 200..299) {
            val body = response.body()
            KLog.d(body.toString())
            if (body != null) {
                listener.onSuccess(body)
            } else {
                listener.onFailure()
            }
        } else {
            // TODO: We could handle much better the errors
//            ErrorManager(response) {
//                listener.onFailure(it)
//            }
        }
    }

    protected fun onComplete(success: Success<JsonElement>, failure: Failure): Listener<JsonElement> {
        return object : Listener<JsonElement> {
            override fun onSuccess(result: JsonElement) = success(result)
            override fun onFailure(serverError: ServerError?) = failure(serverError)
        }
    }
}
