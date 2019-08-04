package com.gabrielmorenoibarra.generic.util.manager

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielmorenoibarra.generic.extension.view.gone
import com.gabrielmorenoibarra.generic.extension.view.showNoResultsPerform
import com.gabrielmorenoibarra.generic.extension.view.visible
import com.gabrielmorenoibarra.generic.ui.view.adapter.base.loader.BaseLoaderAdapter

class PaginationManager<T>(private val rv: RecyclerView,
                           private val pb: ProgressBar?,
                           private val vNoResults: View?,
                           orientation: Int,
                           private val maxPerPage: Int = 10,
                           private val listener: Listener<T>) {

    companion object {
        const val ORIENTATION_VERTICAL = 0
        const val ORIENTATION_HORIZONTAL = 1
    }

    private var hasMoreItems = true
    private var nPage = 1

    interface Listener<T> {
        fun load(nPage: Int, nItems: Int)
        fun populate(items: List<T>)
        fun addItems(items: List<T>)
    }

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
            var b = false
            if (orientation == ORIENTATION_VERTICAL) {
                b = dy > 0
            } else if (orientation == ORIENTATION_HORIZONTAL) {
                b = dx > 0
            }
            if (b) {
                rv.layoutManager?.let {
                    val visibleItemCount = it.childCount
                    val totalItemCount = it.itemCount
                    val pastVisibleItems = (it as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (hasMoreItems) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            hasMoreItems = false
                            setFooterVisible(true)
                            listener.load(nPage, maxPerPage)
                        }
                    }
                }
            }
        }
    }

    init {
        rv.addOnScrollListener(scrollListener)
    }

    fun disableScroll() {
        rv.removeOnScrollListener(scrollListener)
    }

    fun reset() {
        nPage = 0
        hasMoreItems = true
        load()
    }

    fun load() {
        if (hasMoreItems) {
            pb?.visible()
            listener.load(nPage, maxPerPage)
        }
    }

    fun successPerform(items: List<T>) {
        setFooterVisible(false)
        pb?.gone()
        vNoResults?.showNoResultsPerform(nPage, items)
        hasMoreItems = items.size >= maxPerPage

        if (nPage == 0) {
            listener.populate(items)
        } else if (items.isNotEmpty()) {
            listener.addItems(items)
        }
        nPage++
    }

    private fun setFooterVisible(visible: Boolean) {
        (rv.adapter as? BaseLoaderAdapter<*>)?.setFooterVisible(visible)
    }
}
