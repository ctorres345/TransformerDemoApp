package com.pandaveloper.transformersdemo.presentation.ui.summon.dialog.customunit

import com.pandaveloper.transformersdemo.model.TransformerUIModel

sealed class CustomUnitViewState {
    object OnUnitNameError : CustomUnitViewState()
    object OnUnitTeamError : CustomUnitViewState()
    object OnUnitTypeError : CustomUnitViewState()
    object OnUnitStrengthError : CustomUnitViewState()
    object OnUnitIntelligenceError : CustomUnitViewState()
    object OnUnitSpeedError : CustomUnitViewState()
    object OnUnitEnduranceError : CustomUnitViewState()
    object OnUnitRankError : CustomUnitViewState()
    object OnUnitCourageError : CustomUnitViewState()
    object OnUnitFirepowerError : CustomUnitViewState()
    object OnUnitSkillError : CustomUnitViewState()
    data class OnUnitCreationSuccess(val transformer: TransformerUIModel) : CustomUnitViewState()
}