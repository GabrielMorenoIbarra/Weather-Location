package com.gabrielmorenoibarra.generic.ui.view.adapter.base.loader

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabrielmorenoibarra.generic.util.NullableListener

abstract class BaseLoaderAdapter<T>(
    private var items: MutableList<T>,
    private var hasHeader: Boolean = false,
    private var hasFooter: Boolean = false,
    private var n: Long = 0,
    private val listener: NullableListener<T> = null
) : RecyclerView.Adapter<BaseLoaderAdapter.Holder>() {

    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_FOOTER = 1
        const val VIEW_TYPE_ITEM = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> onCreateHeaderViewHolder(parent)
            VIEW_TYPE_FOOTER -> onCreateFooterViewHolder(parent)
            else -> onCreateItemViewHolder(parent, viewType)
        }
    }

    open fun onCreateHeaderViewHolder(parent: ViewGroup): HeaderHolder {
        throw Exception("You need to override this method")
    }

    open fun onCreateFooterViewHolder(parent: ViewGroup): FooterHolder {
        throw Exception("You need to override this method")
    }

    abstract fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): ItemHolder<T>

    override fun onBindViewHolder(holder: Holder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_HEADER -> {
                (holder as HeaderHolder).bind(n)
            }
            VIEW_TYPE_FOOTER -> {
                (holder as FooterHolder).bind()
            }
            else -> {
                var pos = position
                if (hasHeader) {
                    pos--
                }
                val item = items[pos]
                (holder as ItemHolder<T>).bind(this, item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (hasHeader && position == 0) {
            return VIEW_TYPE_HEADER
        } else if (hasFooter && position == itemCount - 1) {
            return VIEW_TYPE_FOOTER
        }
        return getItemViewTypeItem(position)
    }

    open fun getItemViewTypeItem(position: Int): Int {
        return VIEW_TYPE_ITEM
    }

    fun getItem(position: Int): T {
        return items[position]
    }

    override fun getItemCount(): Int {
        var count = items.size
        if (hasFooter) {
            count++
        }
        if (hasHeader) {
            count++
        }
        return count
    }

    fun getItems() = items

    open fun setItems(items: MutableList<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    open fun addItems(items: MutableList<T>) {
        val start = itemCount
        this.items.addAll(items)
        notifyItemRangeInserted(start, items.size)
    }

    open fun addItem(item: T) {
        items.add(item)
        notifyItemInserted(itemCount - 1)
    }

    open fun addItem(item: T, position: Int) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    open fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun click(item: T) {
        listener?.invoke(item)
    }

    fun setFooterVisible(visible: Boolean) {
        val changed = hasFooter != visible
        hasFooter = visible
        if (changed) {
            if (visible) {
                notifyItemInserted(itemCount)
            } else {
                notifyItemRemoved(itemCount - 1)
            }
        }
    }

    abstract class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)

    abstract class HeaderHolder(itemView: View) : Holder(itemView) {
        fun bind(n: Long) = with(itemView) { bindHeader(n) }
        open fun View.bindHeader(n: Long) {}
    }

    abstract class FooterHolder(itemView: View) : Holder(itemView) {
        fun bind() = with(itemView) { bindFooter() }
        abstract fun View.bindFooter()
    }

    abstract class ItemHolder<T>(itemView: View) : Holder(itemView) {
        fun bind(adapter: BaseLoaderAdapter<T>, item: T) = with(itemView) {
            initListener(adapter, item)
            bindItem(adapter, item)
        }

        abstract fun View.bindItem(adapter: BaseLoaderAdapter<T>, item: T)

        open fun View.onItemClick(adapter: BaseLoaderAdapter<T>, item: T) {}

        private fun View.initListener(adapter: BaseLoaderAdapter<T>, item: T) {
            setOnClickListener {
                adapter.click(item)
                onItemClick(adapter, item)
            }
        }
    }
}
