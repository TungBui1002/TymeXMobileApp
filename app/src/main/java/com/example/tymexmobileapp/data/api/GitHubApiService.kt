package com.example.tymexmobileapp.data.api

import com.example.tymexmobileapp.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("per_page") perPage: Int,
        @Query("since") since: Int
    ): List<User>
}
