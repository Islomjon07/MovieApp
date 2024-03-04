package com.example.movieapp.data.cloud.repository

import com.example.movieapp.data.cache.MovieCacheDataSource
import com.example.movieapp.data.cache.dao.MovieCacheModel
import com.example.movieapp.data.cloud.base.BaseDataSource
import com.example.movieapp.data.cloud.base.model.ResultStatus
import com.example.movieapp.data.cloud.mappers.toCache
import com.example.movieapp.data.cloud.mappers.toDataModel
import com.example.movieapp.data.cloud.mappers.toDomain
import com.example.movieapp.data.cloud.remote.MovieService
import com.example.movieapp.domain.models.details.DetailsDomainModel
import com.example.movieapp.domain.models.movie_list.MovieDomain
import com.example.movieapp.domain.repository.GetCurrentMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DefaultGetCurrentMovieRepository(
    private val service: MovieService,
    private val movieCacheDataSource: MovieCacheDataSource,
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
    override suspend fun saveMovieToCache(model: DetailsDomainModel) {
        movieCacheDataSource.addMovieToCache(cacheModel = model.toCache())
    }

    override fun fetchAllSaveMovies(): Flow<List<DetailsDomainModel>> {
        return movieCacheDataSource.fetchAllCacheMovies().mapFlowListMovieToCacheDomain()
    }

    override suspend fun deleteMovieById(movieId: Int) {
        return movieCacheDataSource.deleteMovieById(movieId)
    }

    override fun observeIsMovieSaved(movieId: Int): Flow<Boolean> {
        return movieCacheDataSource.observeIsMovieSaved(movieId)
    }

    private fun Flow<List<MovieCacheModel>>.mapFlowListMovieToCacheDomain() = run {
        this@mapFlowListMovieToCacheDomain.map { listOfModels ->
            listOfModels.map { movieDomain ->
                movieDomain.toDomain()
            }
        }
    }
}
