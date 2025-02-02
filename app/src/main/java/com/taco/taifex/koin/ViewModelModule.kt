package com.taco.taifex.koin

import com.taco.taifex.viewmodel.TaifexViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TaifexViewModel(get()) }
}