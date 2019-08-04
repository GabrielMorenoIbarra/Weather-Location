package com.gabrielmorenoibarra.generic.extension.json

import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

operator fun JSONArray.iterator(): Iterator<JSONObject?> =
        (0 until length()).asSequence().map {
            get(it) as? JSONObject
        }.iterator()

inline fun <reified T> JSONArray.toObjectList(): List<T> {
    val list = mutableListOf<T>()
    iterator().forEach {
        val element = Gson().fromJson(it.toString(), T::class.java)
        list.add(element)
    }
    return list
}
