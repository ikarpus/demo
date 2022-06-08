package com.iskar.demo.sports.data.di

import com.iskar.demo.sports.data.dataSource.SportDataSourceImpl
import com.iskar.demo.sports.data.maping.EventResponseMapper
import com.iskar.demo.sports.data.maping.SportResponseMapper
import com.iskar.demo.sports.data.network.SportsApi
import com.iskar.demo.sports.domain.dataSource.SportDataSource
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val sportsDataModule = module {

    single<SportDataSource> { SportDataSourceImpl(get(), get()) }

    single {
        (get() as Retrofit).create(SportsApi::class.java)
    }

    factory { SportResponseMapper(get()) } bind SportResponseMapper::class
    factory { EventResponseMapper() } bind EventResponseMapper::class
}