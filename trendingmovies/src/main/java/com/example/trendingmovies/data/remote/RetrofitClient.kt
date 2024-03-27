package com.example.trendingmovies.data.remote

import com.example.trendingmovies.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val API_KEY = "api_key"
    private const val API_KEY_VALUE = BuildConfig.API_KEY
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private val authInterceptor: Interceptor = Interceptor { chain ->
        val authenticatedRequest = with(chain.request()) {
            val newUrl = url.newBuilder().addQueryParameter(API_KEY, API_KEY_VALUE).build()
            return@with newBuilder().url(newUrl).build()
        }
        chain.proceed(authenticatedRequest)
    }

    private val okHttpClient = OkHttpClient().newBuilder()
        .apply { interceptors().add(authInterceptor) }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    internal val trendingMoviesService: TrendingMoviesService =
        retrofit.create(TrendingMoviesService::class.java)
}