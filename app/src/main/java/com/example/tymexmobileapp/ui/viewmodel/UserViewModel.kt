package com.example.tymexmobileapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tymexmobileapp.data.model.User
import com.example.tymexmobileapp.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private var since = 100

    fun fetchUsers() {
        viewModelScope.launch {
            val userList = userRepository.getUsers(since = since)
            _users.postValue(userList)
            since += 20 //  fetch next batch of 20 users
        }
    }
}