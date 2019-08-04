package com.gabrielmorenoibarra.weatherlocation.domain.model.page

open class Page(
        protected var hasMore: Boolean = true,
        protected var itemsPerPage: Long = 10,
        protected var page: Long = 1
)
