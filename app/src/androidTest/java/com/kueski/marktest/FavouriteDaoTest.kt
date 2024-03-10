package com.kueski.marktest

import android.content.Context
import com.kueski.marktest.networking.AuthenticationInterceptor
import com.kueski.marktest.networking.NetworkFactory
import com.kueski.marktest.services.MovieService
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavouriteDaoTest {

    private lateinit var factory : NetworkFactory
    @Mock
    lateinit var context : Context
    @Mock
    lateinit var authenticationInterceptor: AuthenticationInterceptor

    fun t() {
//      Mockito.`when`()
       val service =  factory.createService(MovieService::class.java)
//        factory.
    }

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        factory = NetworkFactory(context, authenticationInterceptor)
//        manager = FavouriteMoviesOpsManager(repository)
    }
}