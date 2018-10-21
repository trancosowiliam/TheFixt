package br.com.jwk.thefixt.ext

import android.content.Context
import android.net.ConnectivityManager
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

val Context.isNetworkConnected
    get() = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?)?.activeNetworkInfo != null
