package com.example.tymexmobileapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tymexmobileapp.data.model.User
import com.example.tymexmobileapp.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

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
                val newUsers = repository.getUsers(page = currentPage, )
                _users.value?.let {
                    if (isInitialLoad) {
                        it.clear()
                    }
                    it.addAll(newUsers)
                    _users.value = it
                }
                currentPage++
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
}


