package com.example.trendingmovies.domain.mappers

fun interface ModelMapper<in From, out To> {
    fun mapToDomainModel(from: From): To
}