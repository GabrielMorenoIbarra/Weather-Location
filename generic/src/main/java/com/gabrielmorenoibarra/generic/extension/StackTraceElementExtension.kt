package com.gabrielmorenoibarra.generic.extension

fun StackTraceElement.generateTag() = className.substringAfterLast(".")

fun StackTraceElement.generateMessage(msg: Any) = "$methodName() $msg"
