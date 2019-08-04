package com.gabrielmorenoibarra.generic.ui.view.adapter.base.loader.footer

import android.view.View
import android.view.ViewGroup
import com.gabrielmorenoibarra.generic.R
import com.gabrielmorenoibarra.generic.extension.view.inflate
import com.gabrielmorenoibarra.generic.ui.view.adapter.base.loader.BaseLoaderAdapter
import com.gabrielmorenoibarra.generic.util.NullableListener
import kotlinx.android.synthetic.main.item_footer_vertical_load_more.view.*

abstract class FooterVerticalBaseLoaderAdapter<T>(items: MutableList<T>,
                                                  hasHeader: Boolean = false,
                                                  n: Long = 0,
                                                  listener: NullableListener<T> = null)
    : BaseLoaderAdapter<T>(
        items,
        hasHeader = hasHeader,
        n = n,
        listener = listener) {

    override fun onCreateFooterViewHolder(parent: ViewGroup): BaseLoaderAdapter.FooterHolder {
        return FooterHolder(parent.inflate(R.layout.item_footer_vertical_load_more))
    }

    class FooterHolder(itemView: View) : BaseLoaderAdapter.FooterHolder(itemView) {
        override fun View.bindFooter() {
            contentLpb.show()
        }
    }
}
