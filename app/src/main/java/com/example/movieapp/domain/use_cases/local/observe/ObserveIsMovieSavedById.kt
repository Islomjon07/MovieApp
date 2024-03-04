package com.example.movieapp.domain.use_cases.local.observe

import kotlinx.coroutines.flow.Flow

interface ObserveIsMovieSavedById {
    operator fun invoke(movieId: Int): Flow<Boolean>
}