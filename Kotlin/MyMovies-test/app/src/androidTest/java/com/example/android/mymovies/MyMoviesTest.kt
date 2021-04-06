//package com.example.android.mymovies
//
//import androidx.room.Room
//import com.example.android.mymovies.room.MovieDAO
//import com.example.android.mymovies.room.MovieDatabase
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import java.io.IOException
//
//
///**
// * Copyright 2019, The Android Open Source Project
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//
//import androidx.test.platform.app.InstrumentationRegistry
//import com.example.android.mymovies.pogo.Movie
//import org.junit.Assert.assertEquals
//
////
////
/////**
//// * This is not meant to be a full set of tests. For simplicity, most of your samples do not
//// * include tests. However, when building the Room, it is helpful to make sure it works before
//// * adding the UI.
//// */
////
////@RunWith(AndroidJUnit4::class)
//class MyMoviesTest {
//
//
//    private lateinit var movieDao: MovieDAO
//    private lateinit var db: MovieDatabase
//
//    @Before
//    fun createDb() {
//        val context = InstrumentationRegistry.getInstrumentation().targetContext
//        // Using an in-memory database because the information stored here disappears when the
//        // process is killed.
//        db = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java)
//                // Allowing main thread queries, just for testing.
//                .allowMainThreadQueries()
//                .build()
//        movieDao = db.movieDAO
//    }
//
//    @After
//    @Throws(IOException::class)
//    fun closeDb() {
//        db.close()
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun insertAndGetNight() {
//        val night = Movie()
//        movieDao.insert(night)
//        val tonight = movieDao.getMovie()
//        assertEquals(tonight?.id, 399566)
//    }
//}