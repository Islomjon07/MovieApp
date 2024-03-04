package com.example.movieapp.data.cloud.mappers

import com.example.movieapp.data.cloud.models.details.DetailsDataModel
import com.example.movieapp.data.cloud.models.details.GenreDataModel
import com.example.movieapp.data.cloud.models.details.ProductionCompanyDataModel
import com.example.movieapp.data.cloud.models.details.ProductionCountryDataModel
import com.example.movieapp.data.cloud.models.details.SpokenLanguageDataModel
import com.example.movieapp.data.cloud.models.movies_list.MovieResultList
import com.example.movieapp.data.cloud.utils.Constants.POSTER_PATH_URL
import com.example.movieapp.domain.models.details.DetailsDomainModel
import com.example.movieapp.domain.models.details.GenreModelDomain
import com.example.movieapp.domain.models.details.ProductionCompanyDomainModel
import com.example.movieapp.domain.models.details.ProductionCountryDomainModel
import com.example.movieapp.domain.models.details.SpokenLanguageDomainModel
import com.example.movieapp.domain.models.movie_list.MovieDomain
import com.example.movieapp.presentation.models.details.DetailsUiModel
import com.example.movieapp.presentation.models.details.GenreModelUi
import com.example.movieapp.presentation.models.details.ProductionCompanyUiModel
import com.example.movieapp.presentation.models.details.ProductionCountryUiModel
import com.example.movieapp.presentation.models.details.SpokenLanguageUiModel
import com.example.movieapp.presentation.models.movie_list.MovieUi

fun MovieResultList.toDataModel() = MovieDomain(
    movieVoteCount = movieVoteCount,
    movieVoteAverage = movieVoteAverage,
    movieTitle = movieTitle,
    movieReleaseDate = movieReleaseDate,
    moviePosterPath = POSTER_PATH_URL + moviePosterPath,
    moviePopularity = moviePopularity,
    movieOverview = movieOverview,
    movieId = movieId,
    movieOriginalTitle = movieOriginalTitle,
    movieOriginalLanguage = movieOriginalLanguage,
    movieGenreIds = movieGenreIds,
    movieBackdropPath = movieBackdropPath,
)

fun MovieDomain.toUiModel() = MovieUi(
    movieVoteCount = movieVoteCount,
    movieVoteAverage = movieVoteAverage,
    movieTitle = movieTitle.orEmpty(),
    movieReleaseDate = movieReleaseDate,
    moviePosterPath = POSTER_PATH_URL + moviePosterPath,
    moviePopularity = moviePopularity,
    movieOverview = movieOverview,
    movieId = movieId,
    movieOriginalTitle = movieOriginalTitle,
    movieOriginalLanguage = movieOriginalLanguage,
    movieGenreIds = movieGenreIds,
    movieBackdropPath = movieBackdropPath.orEmpty(),
)

//            DetailsDomain

fun DetailsDataModel.toDataModel() = DetailsDomainModel(
    adult = adult,
    backdropPath = POSTER_PATH_URL + backdropPath,
    budget = budget,
    genres = genres.map { it.toDataModel() },
    homepage = homepage,
    id = id,
    imdbId = imdbId,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    productionCompanies = productionCompanies.map { it.toDataModel() },
    productionCountries = productionCountries.map { it.toDataModel() },
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    spokenLanguages = spokenLanguages.map { it.toDataModel() },
    status = status,
    tagline = tagline,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
)


fun GenreDataModel.toDataModel() = GenreModelDomain(
    id = id,
    name = name,
)

fun ProductionCountryDataModel.toDataModel() = ProductionCountryDomainModel(
    name = name, iso31661 = iso31661
)

fun ProductionCompanyDataModel.toDataModel() = ProductionCompanyDomainModel(
    name = name, id = id, logoPath = logoPath.orEmpty(), originCountry = originCountry
)

fun SpokenLanguageDataModel.toDataModel() = SpokenLanguageDomainModel(
    name = name, iso6391 = iso6391, englishName = englishName
)

fun DetailsDomainModel.toUiModel() = DetailsUiModel(
    adult = adult,
    backdropPath = backdropPath.orEmpty(),
    budget = budget,
    genres = genres.map { it.toUiModel() },
    homepage = homepage,
    id = id,
    imdbId = imdbId,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath.orEmpty(),
    productionCompanies = productionCompanies.map { it.toUiModel() },
    productionCountries = productionCountries.map { it.toUiModel() },
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    spokenLanguages = spokenLanguages.map { it.toUiModel() },
    status = status,
    tagline = tagline,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
)

fun GenreModelDomain.toUiModel() = GenreModelUi(
    id = id,
    name = name,
)

fun ProductionCountryDomainModel.toUiModel() = ProductionCountryUiModel(
    name = name, iso31661 = iso31661
)

fun ProductionCompanyDomainModel.toUiModel() = ProductionCompanyUiModel(
    name = name, id = id, logoPath = logoPath, originCountry = originCountry
)

fun SpokenLanguageDomainModel.toUiModel() = SpokenLanguageUiModel(
    name = name, iso6391 = iso6391, englishName = englishName
)



