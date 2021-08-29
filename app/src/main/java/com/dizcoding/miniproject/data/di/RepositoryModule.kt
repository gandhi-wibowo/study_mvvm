package com.dizcoding.miniproject.data.di

import com.dizcoding.miniproject.data.network.jsonplaceholder.repository.JsonPlaceholderDataStore
import com.dizcoding.miniproject.data.network.jsonplaceholder.repository.JsonPlaceholderRepository
import org.koin.dsl.module

val jsonPlaceholderRepositoryModule = module {
    factory<JsonPlaceholderRepository> { JsonPlaceholderDataStore(get()) }
}