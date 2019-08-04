package com.gabrielmorenoibarra.weatherlocation.framework.project.util.error

import com.gabrielmorenoibarra.generic.extension.json.toObjectList
import com.gabrielmorenoibarra.weatherlocation.framework.project.util.log
import org.json.JSONArray
import org.json.JSONObject

// COMMENT: This class is an example of how could be manage the errors from the backend
class ServerError(var code: Int = -1,
                  var message: String? = null,
                  var input: String? = null,
                  var devInfo: List<String> = mutableListOf()) {

    companion object {
        const val CODE = "code"
        const val MESSAGE = "message"
        const val INPUT = "input"
        const val DEV_INFO = "devInfo"
    }
}

fun String.toServerError(): ServerError {
    if (isNotBlank()) {
        try {
            JSONObject(this).apply {
                val code = optInt(ServerError.CODE, -1)
                val message = optString(ServerError.MESSAGE, null)
                val input = optString(ServerError.INPUT, null)
                val jsonArray = optJSONArray(ServerError.DEV_INFO) ?: JSONArray()
                val devInfo = jsonArray.toObjectList<String>()
                return ServerError(code, message, input, devInfo)
            }
        } catch (e: Exception) {
            e.log()
        }
    }
    return ServerError()
}
