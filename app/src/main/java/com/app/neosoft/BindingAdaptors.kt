package com.app.neosoft

import android.databinding.BindingAdapter
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.view.View

object BindingAdaptors {

    @BindingAdapter("message")
    @JvmStatic fun message(view: View, string: String?) {
        if (!TextUtils.isEmpty(string)) {
            var snackbar = Snackbar
                .make(view, string.toString(), Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }

}