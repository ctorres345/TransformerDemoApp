package com.pandaveloper.transformersdemo.presentation.ui.summon.dialog.customunit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pandaveloper.transformersdemo.enums.UnitTeam
import com.pandaveloper.transformersdemo.enums.UnitType
import com.pandaveloper.transformersdemo.model.UnitStatsUIModel
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertNull

class CustomUnitViewModelTest {
    private val viewModel = CustomUnitViewModel()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `Given a call to the save function with an empty name, the function return an error`() {
        viewModel.saveCustomUnit(name = "", team = null, type = null)
        assertTrue(viewModel.getViewState.value?.peekContent() == CustomUnitViewState.OnUnitNameError)
    }

    @Test
    fun `Given a call to the save function with an invalid team, the function return an error`() {
        viewModel.saveCustomUnit(name = "Test", team = null, type = null)
        assertTrue(viewModel.getViewState.value?.peekContent() == CustomUnitViewState.OnUnitTeamError)
    }

    @Test
    fun `Given a call to the save function with an invalid type, the function return an error`() {
        viewModel.saveCustomUnit(name = "Test", team = UnitTeam.AUTOBOT, type = null)
        assertTrue(viewModel.getViewState.value?.peekContent() == CustomUnitViewState.OnUnitTypeError)
    }

    @Test
    fun `Given a call to the save function with valid fields, the function return success`() {
        viewModel.saveCustomUnit(name = "Test", team = UnitTeam.AUTOBOT, type = UnitType.BASIC)
        assertTrue((viewModel.getViewState.value?.peekContent() is CustomUnitViewState.OnUnitCreationSuccess))
    }

    @Test
    fun `Given a call to the save function with a custom type and no stats, the function does nothing`() {
        viewModel.saveCustomUnit(name = "Test", team = UnitTeam.AUTOBOT, type = UnitType.CUSTOM)
        assertNull(viewModel.getViewState.value)
    }

    @Test
    fun `Given a call to the save function with a custom type and invalid strength stat, the function return error`() {
        viewModel.saveCustomUnit(name = "Test", team = UnitTeam.AUTOBOT, type = UnitType.CUSTOM, customStats = UnitStatsUIModel().apply { strength = 0 })
        assertTrue(viewModel.getViewState.value?.peekContent() == CustomUnitViewState.OnUnitStrengthError)
    }

    @Test
    fun `Given a call to the save function with a custom type and invalid intelligence stat, the function return error`() {
        viewModel.saveCustomUnit(name = "Test", team = UnitTeam.AUTOBOT, type = UnitType.CUSTOM, customStats = UnitStatsUIModel().apply { intelligence = 11 })
        assertTrue(viewModel.getViewState.value?.peekContent() == CustomUnitViewState.OnUnitIntelligenceError)
    }

    @Test
    fun `Given a call to the save function with a custom type and invalid speed stat, the function return error`() {
        viewModel.saveCustomUnit(name = "Test", team = UnitTeam.AUTOBOT, type = UnitType.CUSTOM, customStats = UnitStatsUIModel().apply { speed = 0 })
        assertTrue(viewModel.getViewState.value?.peekContent() == CustomUnitViewState.OnUnitSpeedError)
    }

    @Test
    fun `Given a call to the save function with a custom type and invalid endurance stat, the function return error`() {
        viewModel.saveCustomUnit(name = "Test", team = UnitTeam.AUTOBOT, type = UnitType.CUSTOM, customStats = UnitStatsUIModel().apply { endurance = 11 })
        assertTrue(viewModel.getViewState.value?.peekContent() == CustomUnitViewState.OnUnitEnduranceError)
    }

    @Test
    fun `Given a call to the save function with a custom type and invalid rank stat, the function return error`() {
        viewModel.saveCustomUnit(name = "Test", team = UnitTeam.AUTOBOT, type = UnitType.CUSTOM, customStats = UnitStatsUIModel().apply { rank = 0 })
        assertTrue(viewModel.getViewState.value?.peekContent() == CustomUnitViewState.OnUnitRankError)
    }

    @Test
    fun `Given a call to the save function with a custom type and invalid courage stat, the function return error`() {
        viewModel.saveCustomUnit(name = "Test", team = UnitTeam.AUTOBOT, type = UnitType.CUSTOM, customStats = UnitStatsUIModel().apply { courage = 11 })
        assertTrue(viewModel.getViewState.value?.peekContent() == CustomUnitViewState.OnUnitCourageError)
    }

    @Test
    fun `Given a call to the save function with a custom type and invalid firepower stat, the function return error`() {
        viewModel.saveCustomUnit(name = "Test", team = UnitTeam.AUTOBOT, type = UnitType.CUSTOM, customStats = UnitStatsUIModel().apply { firepower = 0 })
        assertTrue(viewModel.getViewState.value?.peekContent() == CustomUnitViewState.OnUnitFirepowerError)
    }

    @Test
    fun `Given a call to the save function with a custom type and invalid skill stat, the function return error`() {
        viewModel.saveCustomUnit(name = "Test", team = UnitTeam.AUTOBOT, type = UnitType.CUSTOM, customStats = UnitStatsUIModel().apply { skill = 11 })
        assertTrue(viewModel.getViewState.value?.peekContent() == CustomUnitViewState.OnUnitSkillError)
    }

    @Test
    fun `Given a call to the save function with a custom type and valid stats, the function return success`() {
        viewModel.saveCustomUnit(name = "Test", team = UnitTeam.AUTOBOT, type = UnitType.CUSTOM, customStats = UnitStatsUIModel())
        assertTrue((viewModel.getViewState.value?.peekContent() is CustomUnitViewState.OnUnitCreationSuccess))
    }
}