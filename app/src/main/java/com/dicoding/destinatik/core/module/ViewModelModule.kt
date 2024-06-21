package com.dicoding.destinatik.core.module

import com.dicoding.destinatik.core.domain.viewmodel.AuthViewModel
import com.dicoding.destinatik.core.domain.viewmodel.MainViewModel
import com.dicoding.destinatik.core.domain.viewmodel.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModule = module {
    viewModelOf(::AuthViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::UsersViewModel)
}