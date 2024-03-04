package com.example.movieapp.domain.use_cases.local.delete

interface DeleteMovieById {
    suspend operator fun invoke(movieId: Int)
}