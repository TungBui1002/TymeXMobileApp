package com.example.tymexmobileapp.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tymexmobileapp.R
import com.example.tymexmobileapp.data.api.GithubApiService
import com.example.tymexmobileapp.data.model.User
import com.example.tymexmobileapp.ui.adapter.UserAdapter
import com.example.tymexmobileapp.ui.viewmodel.UserViewModel
import com.example.tymexmobileapp.ui.viewmodel.UserViewModelFactory
import com.example.tymexmobileapp.data.repository.UserRepository

class MainActivity : AppCompatActivity() {

    // Use the factory to create ViewModel
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(UserRepository(GithubApiService))  // Pass repository or any required parameter here
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView_Main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter { user -> onUserClicked(user) }
        recyclerView.adapter = userAdapter

        userViewModel.users.observe(this, Observer { users ->
            userAdapter.submitList(users)
        })

        userViewModel.fetchUsers()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    userViewModel.fetchUsers()  // Fetch next 20 users when reaching the bottom
                }
            }
        })
    }

    private fun onUserClicked(user: User) {
        // Handle user click (e.g., navigate to user detail page)
    }
}
