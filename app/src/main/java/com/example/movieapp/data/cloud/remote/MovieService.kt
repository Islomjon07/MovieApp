package com.example.movieapp.data.cloud.remote

import com.example.movieapp.data.cloud.models.details.DetailsDataModel
import com.example.movieapp.data.cloud.models.movies_list.MovieModel
import com.example.movieapp.data.cloud.models.reviews.MovieReviewsModel
import com.example.movieapp.data.cloud.utils.Constants.GET_MOVIE_DETAILS_ID
import com.example.movieapp.data.cloud.utils.Constants.GET_MOVIE_NOW_PLAYING_MOVIE
import com.example.movieapp.data.cloud.utils.Constants.GET_MOVIE_POPULAR
import com.example.movieapp.data.cloud.utils.Constants.GET_MOVIE_REVIEWS_ID
import com.example.movieapp.data.cloud.utils.Constants.GET_MOVIE_SEARCH_ID
import com.example.movieapp.data.cloud.utils.Constants.GET_MOVIE_TOP_RATED
import com.example.movieapp.data.cloud.utils.Constants.GET_MOVIE_UPCOMING_MOVIE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(GET_MOVIE_TOP_RATED)
    suspend fun topRatedMovieServise(
        @Query("language") language: String = "ru-RU"
    ): Response<MovieModel>

    @GET(GET_MOVIE_POPULAR)
    suspend fun popularMovieServise(
        @Query("language") language: String = "ru-RU"
    ): Response<MovieModel>

    @GET(GET_MOVIE_NOW_PLAYING_MOVIE)
    suspend fun nowPlayingMovieServise(
        @Query("language") language: String = "ru-RU"
    ): Response<MovieModel>

    @GET(GET_MOVIE_UPCOMING_MOVIE)
    suspend fun upcomingMovieServise(
        @Query("language") language: String = "ru-RU"
    ): Response<MovieModel>

    @GET(GET_MOVIE_DETAILS_ID)
    suspend fun fetchMovieByIdInfo(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "ru-RU"
    ): Response<DetailsDataModel>

    @GET(GET_MOVIE_SEARCH_ID)
    suspend fun searchByQuery(
        @Query("query") textQuery: String,
        @Query("language") language: String = "ru-RU"
    ): Response<MovieModel>

    @GET(GET_MOVIE_REVIEWS_ID)
    suspend fun fetchMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "ru-RU"
    ): Response<MovieReviewsModel>
}