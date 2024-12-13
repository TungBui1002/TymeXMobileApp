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
    private val userList = mutableListOf<User>()

    // Load users initially or load more users
    fun loadUsers(isLoadMore: Boolean = false) {
        if (isLoadMore) {
            currentPage++
        }

        viewModelScope.launch {
            val newUsers = repository.getUsers(currentPage)
            if (isLoadMore) {
                userList.addAll(newUsers)
            } else {
                userList.clear()
                userList.addAll(newUsers)
            }
            _users.value = userList
        }
    }
}


