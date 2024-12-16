package com.example.tymexmobileapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tymexmobileapp.data.model.User
import com.example.tymexmobileapp.data.repository.UserRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository, private val context: Context) : ViewModel() {

    private val _users = MutableLiveData<MutableList<User>>()
    val users: LiveData<MutableList<User>> get() = _users

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private var currentPage = 1

    init {
        _users.value = mutableListOf()
    }

    fun loadUsers(isInitialLoad: Boolean = false) {
        if (isInitialLoad) _loading.value = true

        viewModelScope.launch {
            try {
                val newUsers = repository.getUsers(page = currentPage)
                _users.value?.let {
                    if (isInitialLoad) {
                        it.clear()
                    }
                    it.addAll(newUsers)
                    _users.value = it
                }

                saveUsersToPreferences(_users.value!!)

                currentPage++
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }


    fun loadUsersFromPreferences() {
        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString("users_list", null)
        if (json != null) {
            val type = object : TypeToken<List<User>>() {}.type
            val users = Gson().fromJson<List<User>>(json, type)
            _users.value = users.toMutableList()
        }
    }

    fun saveUsersToPreferences(users: List<User>) {
        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val json = Gson().toJson(users)
        editor.putString("users_list", json)
        editor.apply()
    }
}
