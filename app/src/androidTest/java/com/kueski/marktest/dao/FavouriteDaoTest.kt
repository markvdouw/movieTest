package com.kueski.marktest.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.kueski.marktest.data.KueskiDb
import com.kueski.marktest.data.dao.FavouriteMoviesDao
import com.kueski.marktest.getMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.CountDownLatch
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class FavouriteDaoTest {

    private lateinit var db: KueskiDb
    private lateinit var dao: FavouriteMoviesDao

    @Test
    fun testInsertMovie() = runBlocking {
        val movie = getMovie(123).toBusiness()!!.toEntity()!!
        dao.insertMovie(movie)
        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            val values = dao.getFavouriteMovies()

            Assert.assertEquals(1, values.size)
            Assert.assertEquals(movie, dao.getFavouriteMovies()[0])
            latch.countDown()
        }
        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun testDeleteMovie() = runBlocking {
        val movie = getMovie(123).toBusiness()!!.toEntity()!!
        dao.insertMovie(movie)
        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            val values = dao.getFavouriteMovies()

            Assert.assertEquals(1, values.size)
            Assert.assertEquals(movie, dao.getFavouriteMovies()[0])
            latch.countDown()
        }
        latch.await()
        job.cancelAndJoin()

        dao.deleteMovie(123)
        val secondLatch = CountDownLatch(1)
        val secondJob = async(Dispatchers.IO) {
            val values = dao.getFavouriteMovies()

            assertTrue(values.isEmpty())
            secondLatch.countDown()
        }
        secondLatch.await()
        secondJob.cancelAndJoin()
    }

    @Test
    fun testExistingMovieById() = runBlocking {
        val movie = getMovie(123).toBusiness()!!.toEntity()!!
        dao.insertMovie(movie)
        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            val query = dao.getFavouriteMovieById(123)

            assertNotNull(query)
            assertEquals(movie, query)
            latch.countDown()
        }
        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun testNotExistingMovieById() = runBlocking {
        val movie = getMovie(1).toBusiness()!!.toEntity()!!
        dao.insertMovie(movie)
        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            val query = dao.getFavouriteMovieById(123)

            assertNull(query)
            latch.countDown()
        }
        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun testGetFavouriteMoviesMovies() = runBlocking {
        val movie = getMovie(123).toBusiness()!!.toEntity()!!
        val secondMovie = getMovie(234).toBusiness()!!.toEntity()!!
        dao.insertMovie(movie)
        dao.insertMovie(secondMovie)
        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            val query = dao.getFavouriteMovies()

            assertEquals(2, query.size)
            query.forEach {
                assertTrue { it == movie || it == secondMovie }
            }
            latch.countDown()
        }
        latch.await()
        job.cancelAndJoin()
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            KueskiDb::class.java
        ).allowMainThreadQueries().build()

        dao = db.favouriteMoviesDao()
    }

    @After
    fun closeDatabase() {
        db.close()
    }
}