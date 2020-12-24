package com.pandaveloper.transformersdemo.presentation.ui.inventory

import com.pandaveloper.transformersdemo.model.SummonedUnitUIModel
import com.pandaveloper.transformersdemo.model.TransformerUIModel

sealed class InventoryViewState {
    object OnLoading : InventoryViewState()
    data class ShowUnitDetailDialog(val unit: SummonedUnitUIModel) : InventoryViewState()
    data class ShowEditDialog(val transformer: TransformerUIModel) : InventoryViewState()
    data class OnInventoryReceivedSuccess(val transformerList: List<TransformerUIModel>) : InventoryViewState()
    data class OnInventoryReceivedError(val errorMessage: String) : InventoryViewState()
    data class OnTransformerDeletedSuccess(val transformerList: List<TransformerUIModel>) : InventoryViewState()
    data class OnTransformerDeletedError(val errorMessage: String) : InventoryViewState()
    data class OnTransformerEditedSuccess(val transformerList: List<TransformerUIModel>) : InventoryViewState()
    data class OnTransformerEditedError(val errorMessage: String) : InventoryViewState()
}