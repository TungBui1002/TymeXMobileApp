package com.example.tymexmobileapp.data.repository

import com.example.tymexmobileapp.data.api.GitHubApiService
import com.example.tymexmobileapp.data.model.User

class UserRepository(private val api: GitHubApiService) {

    suspend fun getUsers(page: Int):List<User>{
        return api.getUsers(page=page)
    }
}

