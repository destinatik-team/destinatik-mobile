package com.dicoding.destinatik.core.module

import com.dicoding.destinatik.core.domain.repository.MainRepository
import com.dicoding.destinatik.core.domain.repository.auth.AuthRepository
import com.dicoding.destinatik.core.domain.repository.users.UsersRepository
import org.koin.core.scope.get
import org.koin.dsl.module

val repoModule = module {
    single { AuthRepository(get()) }
    single { MainRepository(get(), get()) }
    single { UsersRepository(get()) }
}