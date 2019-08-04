package com.gabrielmorenoibarra.generic.extension.view

import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.gabrielmorenoibarra.g.GShowHideKeyboard

fun EditText.setOnTextChangedListener(onTextChanged: (charSequence: CharSequence?) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged(charSequence)
        }

        override fun afterTextChanged(editable: Editable?) {
        }
    })
}

fun EditText.openKeyboardDelayed(delayInMillis: Long = 1000) {
    Handler().postDelayed({
        GShowHideKeyboard(context, this).show()
    }, delayInMillis)
}
