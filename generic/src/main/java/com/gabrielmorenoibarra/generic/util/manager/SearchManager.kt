package com.gabrielmorenoibarra.generic.util.manager

import android.os.Handler
import android.os.Looper
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import com.gabrielmorenoibarra.generic.extension.view.gone
import com.gabrielmorenoibarra.generic.extension.view.setOnTextChangedListener
import com.gabrielmorenoibarra.generic.extension.view.visible

class SearchManager(private val et: EditText,
                    val ib: ImageButton,
                    private val pb: ProgressBar,
                    listener: (String) -> Unit) {

    companion object {
        const val CHARS_SEARCH_LENGTH = 1
    }

    private var searchHandler: Handler = Handler(Looper.getMainLooper())

    init {
        et.clearFocus()
        et.setOnTextChangedListener { s ->
            search(s.toString())
        }
        ib.setOnClickListener {
            et.setText("")
        }
    }

    private fun search(s: String) {
        searchHandler.removeCallbacks(searchRunnable)
        val sTrimmed = s.trim()
        if (s.isEmpty()) {
            ib.gone()
        } else {
            ib.visible()
        }
        if (sTrimmed.isEmpty() || sTrimmed.length < CHARS_SEARCH_LENGTH) {
            showProgressBar(false)
        } else if (sTrimmed.length >= CHARS_SEARCH_LENGTH) {
            showProgressBar(true)
        }
        searchHandler.postDelayed(searchRunnable, 500)
    }

    private var searchRunnable = Runnable {
        val s = et.text.toString()
        listener(s)
    }

    fun showProgressBar(show: Boolean) = if (show) pb.visible() else pb.gone()
}
