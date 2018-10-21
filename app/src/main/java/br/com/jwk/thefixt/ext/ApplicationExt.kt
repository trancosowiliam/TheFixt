package br.com.jwk.thefixt.ext

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog

fun Context.makeDialog(showTitle: String?, showMessage: String?): MaterialDialog {
    return MaterialDialog(this).apply {
        showTitle?.takeUnless { it.isNullOrEmpty() }?.let {
            this.title(text = it)
        }

        showMessage?.takeUnless { it.isNullOrEmpty() }?.let {
            this.message(text = it)
        }
    }
}