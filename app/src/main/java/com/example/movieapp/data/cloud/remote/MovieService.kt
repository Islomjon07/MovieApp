package com.example.movieapp.data.cloud.remote

import com.example.movieapp.data.cloud.models.MovieModel
import com.example.movieapp.data.cloud.utils.Constants.GET_MOVIE_DETAILS_ID
import com.example.movieapp.data.cloud.utils.Constants.GET_MOVIE_NOW_PLAYING_MOVIE
import com.example.movieapp.data.cloud.utils.Constants.GET_MOVIE_POPULAR
import com.example.movieapp.data.cloud.utils.Constants.GET_MOVIE_TOP_RATED
import com.example.movieapp.data.cloud.utils.Constants.GET_MOVIE_UPCOMING_MOVIE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET(GET_MOVIE_TOP_RATED)
    suspend fun topRatedMovieServise(): Response<MovieModel>

    @GET(GET_MOVIE_POPULAR)
    suspend fun popularMovieServise(): Response<MovieModel>

    @GET(GET_MOVIE_NOW_PLAYING_MOVIE)
    suspend fun nowPlayingMovieServise(): Response<MovieModel>

    @GET(GET_MOVIE_UPCOMING_MOVIE)
    suspend fun upcomingMovieServise(): Response<MovieModel>

    @GET(GET_MOVIE_DETAILS_ID)
    suspend fun fetchMovieById(
        @Query("movie_id") movieId: Int
    ): Response<MovieModel>
}