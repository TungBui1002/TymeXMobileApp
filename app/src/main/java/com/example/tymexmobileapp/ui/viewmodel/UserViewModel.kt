package com.example.tymexmobileapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tymexmobileapp.data.model.User
import com.example.tymexmobileapp.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private var currentPage = 1

    fun loadUsers() {
        viewModelScope.launch {
            val userList = repository.getUsers(currentPage)
            _users.value = userList
            currentPage++  // Increment page for next load
        }
    }
}

