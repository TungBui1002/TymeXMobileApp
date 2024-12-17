package com.example.tymexmobileapp.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tymexmobileapp.data.model.User
import com.example.tymexmobileapp.data.repository.UserRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import kotlin.math.log

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<MutableList<User>>()
    val users: LiveData<MutableList<User>> get() = _users

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private var currentSince = 0

    init {
        _users.value = mutableListOf()
    }

    fun loadUsers(isInitialLoad: Boolean = false) {
        if (isInitialLoad) {
            currentSince = 0
            _loading.value = true
        }

        viewModelScope.launch {
            try {
                val newUsers = repository.getUsers(since = currentSince)
                _users.value?.let {
                    if (isInitialLoad) {
                        it.clear()
                    }
                    it.addAll(newUsers)
                    _users.postValue(it)
                }
                currentSince += 20
                repository.saveUsersToPreferences(_users.value!!) // Gọi hàm từ repository
            } catch (e: Exception) {
                _error.value = "Failed to load users: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }

    fun loadUsersFromPreferences() {
        val users = repository.loadUsersFromPreferences()
        _users.value = users.toMutableList()
    }

    private val _userDetail = MutableLiveData<User>()
    val userDetail: LiveData<User> get() = _userDetail

    fun loadUserDetails(username: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val user = repository.getUserDetails(username)
                _userDetail.value = user
            } catch (e: Exception) {
                _error.value = "Failed to load user details: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }
}

