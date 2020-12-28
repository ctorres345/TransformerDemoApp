package com.pandaveloper.transformersdemo.presentation.ui.inventory.dialog.edit

import com.pandaveloper.transformersdemo.model.TransformerUIModel

sealed class EditUnitViewState {
    object OnUnitNameError : EditUnitViewState()
    object OnUnitTeamError : EditUnitViewState()
    object OnUnitTypeError : EditUnitViewState()
    object OnUnitStrengthError : EditUnitViewState()
    object OnUnitIntelligenceError : EditUnitViewState()
    object OnUnitSpeedError : EditUnitViewState()
    object OnUnitEnduranceError : EditUnitViewState()
    object OnUnitRankError : EditUnitViewState()
    object OnUnitCourageError : EditUnitViewState()
    object OnUnitFirepowerError : EditUnitViewState()
    object OnUnitSkillError : EditUnitViewState()
    data class OnUnitUpdated(val transformer: TransformerUIModel) : EditUnitViewState()
}