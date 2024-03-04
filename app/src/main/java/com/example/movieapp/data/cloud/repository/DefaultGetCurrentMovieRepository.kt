package com.example.movieapp.data.cloud.repository

import com.example.movieapp.data.cloud.base.BaseDataSource
import com.example.movieapp.data.cloud.base.model.ResultStatus
import com.example.movieapp.data.cloud.mappers.toDataModel
import com.example.movieapp.data.cloud.remote.MovieService
import com.example.movieapp.domain.models.details.DetailsDomainModel
import com.example.movieapp.domain.models.movie_list.MovieDomain
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

    override suspend fun fetchSearchMovie(textQuery: String): ResultStatus<List<MovieDomain>> {
        val response = invokeResponseRequest {
            service.searchByQuery(textQuery)
        }
        return ResultStatus(status = response.status,
            errorThrowable = response.errorThrowable,
            data = response.data?.movies?.map { it.toDataModel() })
    }

    override suspend fun fetchMovieByIdInfo(movieId: Int): ResultStatus<DetailsDomainModel> {
        val response = invokeResponseRequest {
            service.fetchMovieByIdInfo(movieId = movieId)
        }
        return ResultStatus(
            status = response.status,
            errorThrowable = response.errorThrowable,
            data = response.data?.toDataModel()
        )
    }
}
