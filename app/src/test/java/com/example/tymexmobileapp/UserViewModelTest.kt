package com.example.tymexmobileapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.tymexmobileapp.data.model.User
import com.example.tymexmobileapp.data.repository.UserRepository
import com.example.tymexmobileapp.ui.viewmodel.UserViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: UserRepository = mockk()
    private lateinit var viewModel: UserViewModel
    private val observer: Observer<MutableList<User>> = mockk(relaxed = true)
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        viewModel = UserViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadUsers updates users LiveData`() = runTest {
        val mockUsers = mutableListOf(
            User("user1", 1, "", "", "", "", "", "", "", "", "", "", "", "", "" ),
            User("user2", 2, "", "", "", "", "", "", "", "", "", "", "", "", "")
        )

        coEvery { repository.getUsers(0) } returns mockUsers

        viewModel.users.observeForever(observer)

        viewModel.loadUsers(true)

        testDispatcher.scheduler.advanceUntilIdle()

        verify { observer.onChanged(mockUsers) }
    }
}
