package com.gabrielmorenoibarra.weatherlocation.framework.project.util.error

import com.gabrielmorenoibarra.generic.util.KLog
import com.gabrielmorenoibarra.weatherlocation.framework.project.util.logError
import com.google.gson.JsonElement
import retrofit2.Response

class ErrorManager(response: Response<JsonElement>,
                   listener: (ServerError?) -> Unit) {

    companion object {
        private const val UNSATISFIABLE_REQUEST = 504
    }

    init {
        val errorBody = response.errorBody()
        if (errorBody != null) {
            val message = response.message()
            val url = response.raw().request().url().toString()
            val string = errorBody.string()
            val serverError = string.toServerError()
            val code = response.code()
            if (code in 400..499) {
                val s = "$code $message\n$url\n$string}"
                log(s, serverError.code)
            }
            if (code == 500) {
                "500".logError()
            }
            listener(serverError)
        } else {
            "Error body == null".logError()
            listener(null)
        }
    }

    fun log(s: String, errorCode: Int) {
        if (errorCode != UNSATISFIABLE_REQUEST) {
            s.logError()
        } else {
            KLog.e(s)
        }
    }
}
