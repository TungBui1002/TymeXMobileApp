package com.example.tymexmobileapp.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.tymexmobileapp.data.api.RetrofitInstance
import com.example.tymexmobileapp.data.model.User
import com.example.tymexmobileapp.data.repository.UserRepository
import com.example.tymexmobileapp.databinding.ActivityUserDetailBinding
import com.example.tymexmobileapp.ui.viewmodel.UserViewModel
import com.example.tymexmobileapp.ui.viewmodel.UserViewModelFactory

class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding
    private val viewModel: UserViewModel by viewModels{
        UserViewModelFactory(UserRepository(RetrofitInstance.api,this),this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backUsserDetails.setOnClickListener {
            finish()
        }

        // intent load name and avatar
        val username = intent.getStringExtra("username") ?: return
        val avatarUrl = intent.getStringExtra("avatarUrl") ?: ""

        viewModel.loadUserDetails(username)

        viewModel.userDetail.observe(this){ user ->
            user?.let { displayUserDetails(user) }
        }

        viewModel.loading.observe(this){isLoading ->
            binding.progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this){ error ->
            Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
        }
    }
    private fun displayUserDetails(user: User){
        binding.nameUser.text = user.login
        binding.textLocated.text = user.location?:"Location not available"
        binding.linkBlog.text = user.blog ?: "No blog"
        binding.tvFollower.text = "${user.followers}+"
        binding.tvFollowing.text = "${user.following}+"

        Glide.with(this)
            .load(user.avatar_url)
            .placeholder(android.R.drawable.ic_menu_report_image)
            .into(binding.avatar)
    }
}