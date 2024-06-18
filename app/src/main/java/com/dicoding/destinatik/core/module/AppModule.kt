package com.gagak.farmshields.core.modules

import com.dicoding.destinatik.core.module.repoModule
import com.dicoding.destinatik.core.module.viewModule
import org.koin.dsl.module

val appModule = module {
    includes(
        viewModule,
        repoModule
    )
}