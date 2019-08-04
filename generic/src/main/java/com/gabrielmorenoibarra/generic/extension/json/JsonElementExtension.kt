package com.gabrielmorenoibarra.generic.extension.json

import com.gabrielmorenoibarra.generic.extension.toJSONArray
import com.google.gson.Gson
import com.google.gson.JsonElement

inline fun <reified T> JsonElement.toObject(): T {
    return Gson().fromJson(toString(), T::class.java)
}

inline fun <reified T> JsonElement.toObjectList(): List<T> {
    val list = mutableListOf<T>()
    toJSONArray().iterator().forEach {
        val element = Gson().fromJson(it.toString(), T::class.java)
        list.add(element)
    }
    return list
}
