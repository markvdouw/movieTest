package com.kueski.marktest.di

import android.content.Context
import androidx.room.Room
import com.kueski.marktest.api_client.MovieApiClientImpl
import com.kueski.marktest.api_client.MoviesApiClient
import com.kueski.marktest.business.repository.MoviesRepository
import com.kueski.marktest.data.FavouriteMoviesDao
import com.kueski.marktest.data.KueskiDb
import com.kueski.marktest.networking.AuthenticationInterceptor
import com.kueski.marktest.networking.NetworkFactory
import org.koin.core.module.Module
import org.koin.dsl.module

val repositories = module {
    single<MoviesRepository> { MoviesRepository(get<MoviesApiClient>(), get<FavouriteMoviesDao>()) }
}

val apiClient = module {
    factory<MoviesApiClient> { MovieApiClientImpl(get<NetworkFactory>()) }
    factory<NetworkFactory> { NetworkFactory(get<Context>(), get<AuthenticationInterceptor>()) }
    single<AuthenticationInterceptor> { AuthenticationInterceptor() }
}

val database = module {
    single<KueskiDb> {
        Room.databaseBuilder(get<Context>(), KueskiDb::class.java, "kueskiTestDb").build()
    }
    single<FavouriteMoviesDao> { get<KueskiDb>().favouriteMoviesDao() }
}