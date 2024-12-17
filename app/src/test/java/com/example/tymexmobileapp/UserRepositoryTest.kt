package com.example.tymexmobileapp

import com.example.tymexmobileapp.data.api.GitHubApiService
import com.example.tymexmobileapp.data.model.User
import com.example.tymexmobileapp.data.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserRepositoryTest {
    private lateinit var repository: UserRepository
    private val apiService: GitHubApiService = mockk()
    private val context = mockk<android.content.Context>(relaxed = true)

    @Before
    fun setUp(){
        repository = UserRepository(apiService,context)
    }
    @Test
    fun `getUsers return a list of users`()= runBlocking{

        val mockUsers = listOf(
            User("jvantuyl",101,"","","","","","","","","","","","",""),
            User("user2",2,"","","","","","","","","","","","",""),
            User("freeformz",3,"","","","","","","","","","","","",""),
        )
        coEvery { apiService.getUsers(20,0) } returns mockUsers

        val result = repository.getUsers(0)

        assertEquals(3, result.size)
        assertEquals("jvantuyl", result[0].login)
        assertEquals("user2", result[1].login)
        assertEquals("freeformz", result[2].login)
    }
}