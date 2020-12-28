package com.pandaveloper.transformersdemo.presentation.ui.inventory.dialog.edit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pandaveloper.transformersdemo.enums.UnitTeam
import com.pandaveloper.transformersdemo.enums.UnitType
import com.pandaveloper.transformersdemo.model.TransformerUIModel
import com.pandaveloper.transformersdemo.model.UnitStatsUIModel
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class EditUnitViewModelTest {
    private val viewModel = EditUnitViewModel()
    private val testOriginalUnit = TransformerUIModel(name = "test", team = UnitTeam.AUTOBOT)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `Given a call to the update function with an empty name, the function return an error`() {
        viewModel.updateUnit(originalUnit = testOriginalUnit, name = "", team = null, type = null)
        assertTrue(viewModel.getViewState.value?.peekContent() == EditUnitViewState.OnUnitNameError)
    }

    @Test
    fun `Given a call to the update function with an invalid team, the function return an error`() {
        viewModel.updateUnit(originalUnit = testOriginalUnit, name = "Test", team = null, type = null)
        assertTrue(viewModel.getViewState.value?.peekContent() == EditUnitViewState.OnUnitTeamError)
    }

    @Test
    fun `Given a call to the update function with an invalid type, the function return an error`() {
        viewModel.updateUnit(originalUnit = testOriginalUnit, name = "Test", team = UnitTeam.AUTOBOT, type = null)
        assertTrue(viewModel.getViewState.value?.peekContent() == EditUnitViewState.OnUnitTypeError)
    }

    @Test
    fun `Given a call to the update function with valid fields, the function return success`() {
        viewModel.updateUnit(originalUnit = testOriginalUnit, name = "Test", team = UnitTeam.AUTOBOT, type = UnitType.BASIC)
        assertTrue((viewModel.getViewState.value?.peekContent() is EditUnitViewState.OnUnitUpdated))
    }

    @Test
    fun `Given a call to the update function with a custom type and no stats, the function does nothing`() {
        viewModel.updateUnit(originalUnit = testOriginalUnit, name = "Test", team = UnitTeam.AUTOBOT, type = UnitType.CUSTOM)
        kotlin.test.assertNull(viewModel.getViewState.value)
    }

    @Test
    fun `Given a call to the update function with a custom type and invalid stats, the function return error`() {
        viewModel.updateUnit(originalUnit = testOriginalUnit, name = "Test", team = UnitTeam.AUTOBOT, type = UnitType.CUSTOM, customStats = UnitStatsUIModel().apply { strength = 0 })
        assertTrue(viewModel.getViewState.value?.peekContent() == EditUnitViewState.OnUnitStrengthError)
    }

    @Test
    fun `Given a call to the update function with a custom type and valid stats, the function return success`() {
        viewModel.updateUnit(originalUnit = testOriginalUnit, name = "Test", team = UnitTeam.AUTOBOT, type = UnitType.CUSTOM, customStats = UnitStatsUIModel())
        assertTrue((viewModel.getViewState.value?.peekContent() is EditUnitViewState.OnUnitUpdated))
    }
}