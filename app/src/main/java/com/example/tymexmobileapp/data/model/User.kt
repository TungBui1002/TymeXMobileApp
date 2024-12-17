package com.example.tymexmobileapp.data.model

data class User(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,

    //user details
    val name: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val followers: String,
    val following: String,
    val created_at: String,
    val updated_at: String
)

