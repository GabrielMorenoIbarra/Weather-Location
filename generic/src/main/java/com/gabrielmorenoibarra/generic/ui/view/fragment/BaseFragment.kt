package com.gabrielmorenoibarra.generic.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.gabrielmorenoibarra.generic.util.VoidListener

open class BaseFragment : Fragment() {

    private val listeners: MutableList<VoidListener> = mutableListOf()
    private var viewCreated = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewCreated = true
        listeners.forEach { it() }
        listeners.clear()
    }

    override fun onDestroyView() {
        viewCreated = false
        super.onDestroyView()
    }

    protected fun doWhenViewCreated(success: VoidListener) {
        if (viewCreated) {
            success()
        } else {
            listeners.add(success)
        }
    }
}
