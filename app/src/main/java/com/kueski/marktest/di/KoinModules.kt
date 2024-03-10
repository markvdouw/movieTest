package com.kueski.marktest.di

import android.content.Context
import androidx.room.Room
import com.kueski.marktest.api_client.MovieApiClientImpl
import com.kueski.marktest.api_client.MoviesApiClient
import com.kueski.marktest.business.manager.FavouriteMoviesOpsManager
import com.kueski.marktest.business.repository.MoviesRepository
import com.kueski.marktest.data.KueskiDb
import com.kueski.marktest.data.dao.FavouriteMoviesDao
import com.kueski.marktest.networking.AuthenticationInterceptor
import com.kueski.marktest.networking.NetworkFactory
import com.kueski.marktest.ui.viewmodel.DetailViewModel
import com.kueski.marktest.ui.viewmodel.FavouriteMoviesViewModel
import com.kueski.marktest.ui.viewmodel.MovieDiscoveryViewModel
import com.kueski.marktest.ui.viewmodel.NowPlayingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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

val managers = module {
    factory<FavouriteMoviesOpsManager> { FavouriteMoviesOpsManager(get<MoviesRepository>()) }
}

val viewModels = module {
    viewModel<MovieDiscoveryViewModel> { MovieDiscoveryViewModel(get<MoviesRepository>()) }
    viewModel<FavouriteMoviesViewModel> { FavouriteMoviesViewModel(get<MoviesRepository>()) }
    viewModel<NowPlayingViewModel> { NowPlayingViewModel(get<MoviesRepository>()) }
    viewModel<DetailViewModel> { DetailViewModel(get<FavouriteMoviesOpsManager>()) }
}