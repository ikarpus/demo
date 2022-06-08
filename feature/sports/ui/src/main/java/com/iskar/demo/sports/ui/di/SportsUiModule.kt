package com.iskar.demo.sports.ui.di

import com.iskar.demo.sports.ui.SportsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val sportsUiModule = module {

    viewModel { SportsViewModel(get()) }

}