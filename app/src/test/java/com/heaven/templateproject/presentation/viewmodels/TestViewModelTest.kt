package com.heaven.templateproject.presentation.viewmodels

import app.cash.turbine.test
import com.heaven.templateproject.domain.base.Outcome
import com.heaven.templateproject.domain.interactors.TestInteractor
import com.heaven.templateproject.presentation.viewmodels.base.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class TestViewModelTest {

    private lateinit var testViewModel: TestViewModel
    private lateinit var testInteractor: TestInteractor
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        testInteractor = mock(TestInteractor::class.java)
        testViewModel = TestViewModel(testInteractor)

        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `test onButtonClick success outcome`() = runTest {
        val mockData = "Test Data"
        whenever(testInteractor.getTestData()).thenReturn(Outcome.Success(mockData))

        testViewModel.onButtonClick()

        testViewModel.uiState.test {
            val state = awaitItem()
            assert(state is ScreenState.Content)
            assert((state as ScreenState.Content).data == mockData) //
        }
    }

    @Test
    fun `test onButtonClick error outcome`() = runTest {
        whenever(testInteractor.getTestData()).thenReturn(Outcome.Error(400, ""))

        testViewModel.onButtonClick()

        testViewModel.uiState.test {
            val stateAfter = awaitItem()
            assert(stateAfter is ScreenState.Error)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}