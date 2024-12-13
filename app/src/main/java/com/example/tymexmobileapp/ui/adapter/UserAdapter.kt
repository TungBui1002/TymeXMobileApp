package com.example.tymexmobileapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tymexmobileapp.R
import com.example.tymexmobileapp.data.model.User
import com.example.tymexmobileapp.databinding.InfoMainItemBinding

class UserAdapter(private val listener: (User) -> Unit) : ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = InfoMainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)

        holder.itemView.setOnClickListener {
            listener(user)
        }
    }

    inner class UserViewHolder(private val binding: InfoMainItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.nameUser.text = user.login
            binding.link.text = user.html_url

            Glide.with(itemView.context)
                .load(user.avatar_url)
                .placeholder(R.drawable.abc)
                .error(R.drawable.abc)
                .into(binding.avatar)

        }
    }

    class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}
