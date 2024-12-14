package com.example.tymexmobileapp.data.model

data class User(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val repos_url: String,
    val type: String
)

