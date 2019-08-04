package com.gabrielmorenoibarra.generic.util

typealias Success<T> = (T) -> Unit
typealias SuccessNullable<T> = (T?) -> Unit

typealias VoidListener = () -> Unit
typealias NullableListener<T> = ((T) -> Unit)?
