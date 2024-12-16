package com.example.tymexmobileapp.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tymexmobileapp.data.model.User
import com.example.tymexmobileapp.databinding.InfoMainItemBinding
import com.example.tymexmobileapp.ui.view.UserDetailActivity

class UserAdapter(private val users: MutableList<User>, private val context: Context) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = InfoMainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, UserDetailActivity::class.java).apply {
                putExtra("username", user.login)
                putExtra("avatarUrl", user.avatar_url)
                putExtra("urlLink", user.url)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = users.size

    fun updateData(newUsers: List<User>) {
        users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()
    }

    fun addData(newUsers: List<User>) {
        val start = users.size
        users.addAll(newUsers)
        notifyItemRangeInserted(start, newUsers.size)
    }

    inner class UserViewHolder(private val binding: InfoMainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.nameUser.text = user.login
            binding.link.text = user.html_url
            Glide.with(binding.avatar.context)
                .load(user.avatar_url)
                .into(binding.avatar)

        }
    }
}




