package com.example.tymexmobileapp.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.tymexmobileapp.data.api.GitHubApiService
import com.example.tymexmobileapp.data.model.User
import com.google.gson.Gson

class UserRepository(private val api: GitHubApiService, private val context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    private val gson = Gson()

    suspend fun getUsers(page: Int): List<User> {
        return api.getUsers(20, page * 20)
    }
}



