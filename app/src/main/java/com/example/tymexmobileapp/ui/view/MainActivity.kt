package com.example.tymexmobileapp.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tymexmobileapp.R
import com.example.tymexmobileapp.data.api.RetrofitInstance
import com.example.tymexmobileapp.ui.adapter.UserAdapter
import com.example.tymexmobileapp.ui.viewmodel.UserViewModel
import com.example.tymexmobileapp.data.repository.UserRepository
import com.example.tymexmobileapp.databinding.ActivityMainBinding
import com.example.tymexmobileapp.ui.viewmodel.UserViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory(UserRepository(RetrofitInstance.api, this),this)
    }

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter(mutableListOf(), this)
        binding.recyclerViewMain.adapter = userAdapter


        binding.recyclerViewMain.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

            binding.recyclerViewMain.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                    if (!viewModel.loading.value!! && visibleItemCount + pastVisibleItems >= totalItemCount) {
                        viewModel.loadUsers() // Tải thêm 20 người dùng
                    }
                }
            })

        }

        viewModel.loadUsersFromPreferences()

        viewModel.users.observe(this, { users ->
            users?.let {
                userAdapter.addData(it)
            }
        })

        viewModel.loading.observe(this, { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        viewModel.error.observe(this, { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.loadUsers(isInitialLoad = true)
    }
}







