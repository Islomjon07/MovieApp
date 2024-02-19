package com.example.movieapp.data.cloud.repository

import com.example.movieapp.data.cloud.base.BaseDataSource
import com.example.movieapp.data.cloud.base.model.ResultStatus
import com.example.movieapp.data.cloud.mappers.toDataModel
import com.example.movieapp.data.cloud.remote.MovieService
import com.example.movieapp.domain.models.MovieDomain
import com.example.movieapp.domain.repository.GetCurrentMovieRepository


class DefaultGetCurrentMovieRepository(
    private val service: MovieService
) : GetCurrentMovieRepository, BaseDataSource() {

    override suspend fun topRatedMovieData(): ResultStatus<List<MovieDomain>> {
        val response = invokeResponseRequest {
            service.topRatedMovieServise()
        }
        return ResultStatus(status = response.status,
            errorThrowable = response.errorThrowable,
            data = response.data?.movies?.map { it.toDataModel() })
    }

    override suspend fun popularMovieData(): ResultStatus<List<MovieDomain>> {
        val response = invokeResponseRequest {
            service.popularMovieServise()
        }
        return ResultStatus(status = response.status,
            errorThrowable = response.errorThrowable,
            data = response.data?.movies?.map { it.toDataModel() })

    }

    override suspend fun nowPlayingMovieData(): ResultStatus<List<MovieDomain>> {
        val response = invokeResponseRequest {
            service.nowPlayingMovieServise()
        }
        return ResultStatus(status = response.status,
            errorThrowable = response.errorThrowable,
            data = response.data?.movies?.map { it.toDataModel() })
    }

    override suspend fun upcomingMovieData(): ResultStatus<List<MovieDomain>> {
        val response = invokeResponseRequest {
            service.upcomingMovieServise()
        }
        return ResultStatus(status = response.status,
            errorThrowable = response.errorThrowable,
            data = response.data?.movies?.map { it.toDataModel() })
    }
}