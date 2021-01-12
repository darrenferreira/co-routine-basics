package com.example.co_routine_basics.basic.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.co_routine_basics.basic.repo.BasicRepo
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

@ExperimentalCoroutinesApi
class BasicViewModelTest {

    private lateinit var basicViewModel: BasicViewModel

    @Mock
    private lateinit var mockRepo: BasicRepo

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        initMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)

        runBlocking {
            whenever(mockRepo.getDataFromService()).thenReturn(listOf("1", "2", "3"))
        }
        basicViewModel = BasicViewModel(mockRepo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testViewModelUsesRepoToFetchData() = runBlockingTest {
        basicViewModel.getMeSomeData()
        verify(mockRepo).getDataFromService()
    }
}