package com.pandaveloper.transformersdemo.presentation.ui.inventory.dialog.edit

import com.pandaveloper.transformersdemo.model.TransformerUIModel

sealed class EditUnitViewState {
    data class OnUnitNameError(val errorMessage: String) : EditUnitViewState()
    data class OnUnitTeamError(val errorMessage: String) : EditUnitViewState()
    data class OnUnitTypeError(val errorMessage: String) : EditUnitViewState()
    data class OnUnitUpdated(val transformer: TransformerUIModel) : EditUnitViewState()
}