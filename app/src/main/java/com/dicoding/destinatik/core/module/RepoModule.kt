package com.dicoding.destinatik.core.module

import com.dicoding.destinatik.core.domain.repository.auth.AuthRepository
import org.koin.core.scope.get
import org.koin.dsl.module

val repoModule = module {
    single { AuthRepository(get()) }
}