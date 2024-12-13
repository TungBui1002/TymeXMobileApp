package com.example.tymexmobileapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tymexmobileapp.data.model.User
import com.example.tymexmobileapp.databinding.InfoMainItemBinding

class UserAdapter(private val users: List<User>, private val clickListener: (User) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = InfoMainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = users.size

    inner class UserViewHolder(private val binding: InfoMainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.nameUser.text = user.login
            binding.link.text = user.html_url
            Glide.with(binding.avatar.context)
                .load(user.avatar_url)
                .into(binding.avatar)
            itemView.setOnClickListener {
                clickListener(user)
            }
        }
    }
}

