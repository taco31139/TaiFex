package com.taco.taifex.koin

import com.taco.taifex.domain.repository.TaifexRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { TaifexRepository(get()) }
}