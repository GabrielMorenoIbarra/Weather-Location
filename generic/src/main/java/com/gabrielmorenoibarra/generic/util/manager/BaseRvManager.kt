package com.gabrielmorenoibarra.generic.util.manager

import android.view.View
import android.widget.ProgressBar
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gabrielmorenoibarra.generic.extension.view.initVerticalLayout
import com.gabrielmorenoibarra.generic.ui.view.adapter.base.loader.footer.FooterVerticalBaseLoaderAdapter

abstract class BaseRvManager<T>(protected val rv: RecyclerView,
                                private val srl: SwipeRefreshLayout?,
                                @DrawableRes val dividerResId: Int,
                                private val vNoResults: View? = null,
                                private val pb: ProgressBar? = null,
                                protected val id: Long = -1,
                                protected val query: String = "",
                                private val orientation: Int = PaginationManager.ORIENTATION_VERTICAL,
                                private val maxPerPage: Int = 10) {

    private lateinit var pm: PaginationManager<T>
    protected var adapter: FooterVerticalBaseLoaderAdapter<T>? = null

    init {
        initRv()
        initPM()
        initSrl()
    }

    private fun initRv() {
        rv.initVerticalLayout(dividerResId)
    }

    private fun initPM() {
        pm = PaginationManager(
                rv,
                pb,
                vNoResults,
                orientation,
                maxPerPage,
                object : PaginationManager.Listener<T> {
                    override fun load(nPage: Int, nItems: Int) {
                        get(nPage, nItems) {
                            pm.successPerform(it)
                            hideSrl()
                        }
                    }

                    override fun populate(items: List<T>) {
                        this@BaseRvManager.populate(items)
                    }

                    override fun addItems(items: List<T>) {
                        this@BaseRvManager.addItems(items)
                    }
                })
    }

    fun disableScroll() {
        pm.disableScroll()
    }

    fun load() {
        pm.load()
    }

    fun reset() {
        pm.reset()
    }

    abstract fun get(nPage: Int, nItems: Int, listener: (List<T>) -> Unit)

    private fun populate(items: List<T>) {
        if (adapter == null) {
            adapter = createAdapter(items)
            rv.adapter = adapter
        } else {
            updateItems(items)
        }
    }

    abstract fun createAdapter(items: List<T>): FooterVerticalBaseLoaderAdapter<T>

    open fun updateItems(items: List<T>) {
        adapter?.setItems(items.toMutableList())
    }

    private fun addItems(items: List<T>) {
        adapter?.addItems(items.toMutableList())
    }

    private fun initSrl() {
        srl?.setOnRefreshListener {
            reset()
        }
    }

    private fun hideSrl() {
        srl?.isRefreshing = false
    }

    fun addItemFirst(item: T) {
        adapter?.addItem(item, 0)
        rv.smoothScrollToPosition(0)
    }

    fun addItem(item: T, position: Int) {
        adapter?.addItem(item, position)
    }
}
