package com.example.tymexmobileapp.data.api

import com.example.tymexmobileapp.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("per_page") perPage: Int = 20,
        @Query("since") since: Int = 100
    ): Response<List<User>>

}