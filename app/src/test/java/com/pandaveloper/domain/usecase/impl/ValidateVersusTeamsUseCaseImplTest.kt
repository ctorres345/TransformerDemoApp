package com.pandaveloper.domain.usecase.impl

import com.pandaveloper.domain.model.result.TeamBattleResult
import com.pandaveloper.domain.model.result.TeamValidationResult
import com.pandaveloper.domain.repository.TransformerRepository
import com.pandaveloper.domain.usecase.ValidateVersusTeamsUseCase
import com.pandaveloper.testUtils.CoroutineTestRule
import com.pandaveloper.testUtils.TestHelper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.withTestContext
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class ValidateVersusTeamsUseCaseImplTest {
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @MockK(relaxed = true)
    private lateinit var mockRepository : TransformerRepository

    private lateinit var usecase : ValidateVersusTeamsUseCase

    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true)
        usecase = ValidateVersusTeamsUseCaseImpl(repository = mockRepository, coroutinesTestRule.testDispatcherProvider)
    }

    @Test
    fun `Given that the repository returns an empty list, the use case returns a NoUnitsError`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { mockRepository.getTransformer() } returns emptyList()
        val result = usecase.execute()
        Assert.assertTrue(result is TeamValidationResult.NoUnitsError)
    }

    @Test
    fun `Given that the repository returns a list with only autobots, the use case returns a NotEnoughDecepticonsError`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { mockRepository.getTransformer() } returns listOf(TestHelper.createAutobot())
        val result = usecase.execute()
        Assert.assertTrue(result is TeamValidationResult.NotEnoughDecepticonsError)
    }

    @Test
    fun `Given that the repository returns a list with only decepticons, the use case returns a NotEnoughAutobotsError`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { mockRepository.getTransformer() } returns listOf(TestHelper.createDecepticon())
        val result = usecase.execute()
        Assert.assertTrue(result is TeamValidationResult.NotEnoughAutobotsError)
    }

    @Test
    fun `Given that the repository returns a valid list, the use case returns Success`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery { mockRepository.getTransformer() } returns listOf(TestHelper.createDecepticon(), TestHelper.createAutobot())
        val result = usecase.execute()
        Assert.assertTrue(result is TeamValidationResult.Success)
    }
}