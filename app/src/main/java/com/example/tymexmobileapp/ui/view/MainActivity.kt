package com.example.tymexmobileapp.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tymexmobileapp.R
import com.example.tymexmobileapp.data.api.RetrofitInstance
import com.example.tymexmobileapp.ui.adapter.UserAdapter
import com.example.tymexmobileapp.ui.viewmodel.UserViewModel
import com.example.tymexmobileapp.data.repository.UserRepository
import com.example.tymexmobileapp.ui.viewmodel.UserViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView_Main)
        recyclerView.layoutManager = LinearLayoutManager(this)

        userAdapter = UserAdapter(emptyList()) { user ->
        }
        recyclerView.adapter = userAdapter

        val userRepository = UserRepository(RetrofitInstance.api)
        val viewModelFactory = UserViewModelFactory(userRepository)
        userViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)

        userViewModel.users.observe(this, Observer {
            userAdapter = UserAdapter(it) { user ->
                // Handle click event here
            }
            recyclerView.adapter = userAdapter
        })

        userViewModel.loadUsers()

        // add OnScrollListener
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItemPosition == totalItemCount - 1) {
                    userViewModel.loadUsers(isLoadMore = true)
                }
            }
        })
    }
}





