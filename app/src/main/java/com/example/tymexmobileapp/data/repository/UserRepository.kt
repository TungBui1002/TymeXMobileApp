package com.example.tymexmobileapp.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.tymexmobileapp.data.api.GitHubApiService
import com.example.tymexmobileapp.data.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserRepository(
    private val api: GitHubApiService,
    private val context: Context? = null
) {

    suspend fun getUsers(since: Int): List<User> {
        return api.getUsers(20, since)
    }

    suspend fun getUserDetails(username: String):User {
        return api.getUserDetails(username)
    }
    private val sharedPreferences: SharedPreferences? =
        context?.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    fun saveUsersToPreferences(users: List<User>) {
        sharedPreferences?.let {
            val editor = it.edit()
            val json = Gson().toJson(users)
            editor.putString("users_list", json)
            editor.apply()
            Log.e("KKK","save data ${users.size}")
        }
    }

    fun loadUsersFromPreferences(): List<User> {
        val json = sharedPreferences?.getString("users_list", null)
        return if (json != null) {
            val type = object : TypeToken<List<User>>() {}.type
            Gson().fromJson(json, type)
        } else {
            emptyList()
        }
    }
}



