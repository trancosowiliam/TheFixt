package br.com.jwk.thefixt.module

import br.com.jwk.thefixt.data.remote.OMDbService
import br.com.jwk.thefixt.data.remote.OMDbServiceImpl
import org.koin.dsl.module.applicationContext

val serviceModule = applicationContext {
    factory { OMDbServiceImpl(retrofit = get()) as OMDbService }
}