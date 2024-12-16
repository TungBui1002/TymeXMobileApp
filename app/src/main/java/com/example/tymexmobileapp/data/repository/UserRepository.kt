package com.example.tymexmobileapp.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.tymexmobileapp.data.api.GitHubApiService
import com.example.tymexmobileapp.data.model.User
import com.google.gson.Gson

class UserRepository(private val api: GitHubApiService, private val context: Context) {

    suspend fun getUsers(since: Int): List<User> {
        return api.getUsers(20, since)
    }

    suspend fun getUserDetails(username: String):User {
        return api.getUserDetails(username)
    }
}



