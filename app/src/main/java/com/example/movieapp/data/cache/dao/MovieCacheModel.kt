package com.example.movieapp.data.cache.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


const val TABLE_NAME = "movies_table_name"

@Entity(tableName = TABLE_NAME)
data class MovieCacheModel(
    @PrimaryKey val id: Int,
    @ColumnInfo("poster_path") val posterPath: String,
    @ColumnInfo("overview") val overview: String,
    @ColumnInfo("release_date") val releaseDate: String,
    @ColumnInfo("original_language") val originalLanguage: String,
    @ColumnInfo("original_title") val originalTitle: String,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("backdrop_path") val backdropPath: String,
    @ColumnInfo("popularity") val popularity: Double,
    @ColumnInfo("vote_count") val voteCount: Int,
    @ColumnInfo("movie_average") val voteAverage: Double,
    @ColumnInfo("runtime") val runtime: Int,
)