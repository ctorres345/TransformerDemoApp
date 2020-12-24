package com.pandaveloper.transformersdemo.presentation.ui.inventory

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pandaveloper.domain.model.result.DeleteResult
import com.pandaveloper.domain.model.result.TransformerResult
import com.pandaveloper.domain.usecase.DeleteTransformerUseCase
import com.pandaveloper.domain.usecase.EditTransformerUseCase
import com.pandaveloper.domain.usecase.GetTransformersUseCase
import com.pandaveloper.transformersdemo.mapper.toDomainModel
import com.pandaveloper.transformersdemo.mapper.toSummonedUnitUIModel
import com.pandaveloper.transformersdemo.mapper.toUIModel
import com.pandaveloper.transformersdemo.model.TransformerUIModel
import com.pandaveloper.transformersdemo.presentation.base.BaseViewModel
import com.pandaveloper.transformersdemo.presentation.callback.TransformerAdapterCallback
import com.pandaveloper.transformersdemo.util.updateFrom
import kotlinx.coroutines.launch

class InventoryViewModel @ViewModelInject constructor(
    private val getTransformersUseCase: GetTransformersUseCase,
    private val editTransformerUseCase: EditTransformerUseCase,
    private val deleteTransformerUseCase: DeleteTransformerUseCase
)  : BaseViewModel(), TransformerAdapterCallback {
    private val viewState: MutableLiveData<InventoryViewState> = MutableLiveData()
    val getViewState: LiveData<InventoryViewState> = viewState
    private val inventoryList : MutableList<TransformerUIModel> = mutableListOf()

    fun getTransformerInventory() {
        viewModelScope.launch {
            val result = when(val response = getTransformersUseCase.execute()) {
                is TransformerResult.Success -> {
                    inventoryList.apply {
                        clear()
                        addAll(response.transformerList.map { it.toUIModel() })
                    }
                    InventoryViewState.OnInventoryReceivedSuccess(inventoryList)
                }
                is TransformerResult.Error -> InventoryViewState.OnInventoryReceivedError(response.errorMessage)
            }
            viewState.postValue(result)
        }
    }

    fun getInventory() = inventoryList.toList()

    override fun onTransformerSelected(transformer: TransformerUIModel) {
        viewState.postValue(InventoryViewState.ShowUnitDetailDialog(transformer.toSummonedUnitUIModel()))
    }

    override fun editTransformer(transformer: TransformerUIModel) {
        viewState.postValue(InventoryViewState.ShowEditDialog(transformer))
    }

    override fun deleteTransformer(transformerId: String) {
        viewState.postValue(InventoryViewState.OnLoading)
        viewModelScope.launch {
            val result = when(val deleteResult = deleteTransformerUseCase.execute(transformerId)) {
                is DeleteResult.Success -> {
                    deleteTransformerUseCase.execute(transformerId)
                    val itemToRemove = inventoryList.firstOrNull { it.id == transformerId }
                    itemToRemove?.let {
                        inventoryList.remove(it)
                    }
                    InventoryViewState.OnTransformerEditedSuccess(getInventory())
                }
                is DeleteResult.Error -> {
                    InventoryViewState.OnTransformerDeletedError(deleteResult.errorMessage)
                }
            }
            viewState.postValue(result)
        }
    }

    fun updateUnit(transformer: TransformerUIModel) {
        viewModelScope.launch {
            val result = try {
                editTransformerUseCase.execute(transformer.toDomainModel())
                val updatedTransformer = inventoryList.firstOrNull { it.id == transformer.id }
                updatedTransformer?.updateFrom(transformer)
                InventoryViewState.OnTransformerEditedSuccess(getInventory())
            } catch (ex: Exception) {
                InventoryViewState.OnTransformerEditedError("Error Updating tne selected transformer. Please try again.")
            }
            viewState.postValue(result)
        }
    }

}