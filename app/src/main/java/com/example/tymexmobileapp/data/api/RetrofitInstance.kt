package com.example.tymexmobileapp.data.api

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitInstance {
    private const val BASE_URL = "https://api.github.com/"
//    private const val ACCESS_TOKEN = "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"

    private val okHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        addInterceptor { chain ->
            val request: Request = chain.request().newBuilder()
//                .addHeader("Authorization", "token $ACCESS_TOKEN")
                .build()
            chain.proceed(request)
        }
    }.build()

    // Tạo Retrofit với OkHttpClient
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: GitHubApiService by lazy {
        retrofit.create(GitHubApiService::class.java)
    }
}
