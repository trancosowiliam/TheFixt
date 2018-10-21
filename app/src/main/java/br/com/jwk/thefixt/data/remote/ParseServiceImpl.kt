package br.com.jwk.thefixt.data.remote

import android.content.Context
import br.com.jwk.thefixt.BuildConfig
import com.parse.Parse
import com.parse.ParseACL
import com.parse.ParseUser

class ParseServiceImpl(private val context: Context) : ParseService {

    override fun init() {
        Parse.enableLocalDatastore(context)

        Parse.initialize(Parse.Configuration.Builder(context)
                .applicationId(BuildConfig.BACKAPP_APP_ID)
                .clientKey(BuildConfig.BACKAPP_CLIENT_KEY)
                .server(BuildConfig.BACKAPP_SERVER_URL)
                .enableLocalDataStore()
                .build()
        )

        val defaultACL = ParseACL()
        defaultACL.publicReadAccess = true
    }
}