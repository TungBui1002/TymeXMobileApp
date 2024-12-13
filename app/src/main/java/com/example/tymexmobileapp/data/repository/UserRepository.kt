package com.example.tymexmobileapp.data.repository

import com.example.tymexmobileapp.data.api.GithubApiService
import com.example.tymexmobileapp.data.model.User

class UserRepository(private val apiService: GithubApiService) {
    suspend fun getUsers(perPage: Int = 20, since: Int = 100): List<User> {
        val response = apiService.getUsers(perPage, since)
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            emptyList()
        }
    }
}
