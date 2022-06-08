package com.iskar.demo.sports.domain.di

import com.iskar.demo.sports.domain.interactor.SportInteractor
import com.iskar.demo.sports.domain.interactor.SportInteractorImpl
import org.koin.dsl.module

val sportsDomainModule = module {

    factory<SportInteractor> { SportInteractorImpl(get()) }
}