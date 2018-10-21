package br.com.jwk.thefixt.module

import br.com.jwk.thefixt.data.remote.*
import org.koin.dsl.module.applicationContext

val serviceModule = applicationContext {
    factory { ParseServiceImpl(context = get()) as ParseService }
    factory { OMDbServiceImpl(retrofit = get()) as OMDbService }
    factory { OwnerServiceImpl() as OwnerService }

}