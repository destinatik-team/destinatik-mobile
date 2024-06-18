package com.dicoding.destinatik.core.module

import com.dicoding.destinatik.core.domain.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModule = module {
    viewModelOf(::AuthViewModel)
}