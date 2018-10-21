package br.com.jwk.thefixt.base

import android.app.Application
import br.com.jwk.thefixt.module.retrofitClientModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    private val modules = listOf(
            retrofitClientModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin(this, modules)
    }
}