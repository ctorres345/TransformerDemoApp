package com.pandaveloper.transformersdemo.presentation.ui.summon

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pandaveloper.domain.model.result.TransformerResult
import com.pandaveloper.domain.usecase.GetRecruitableTransformersUseCase
import com.pandaveloper.domain.usecase.RegisterTransformerUseCase
import com.pandaveloper.transformersdemo.mapper.toDomainModel
import com.pandaveloper.transformersdemo.mapper.toSummonedUnitUIModel
import com.pandaveloper.transformersdemo.mapper.toUIModel
import com.pandaveloper.transformersdemo.model.TransformerUIModel
import com.pandaveloper.transformersdemo.presentation.base.BaseViewModel
import com.pandaveloper.transformersdemo.util.Constants
import kotlinx.coroutines.launch

class SummonViewModel @ViewModelInject constructor(
    private val getRecruitableTransformersUseCase: GetRecruitableTransformersUseCase,
    private val registerTransformerUseCase: RegisterTransformerUseCase
) : BaseViewModel() {
    private val viewState: MutableLiveData<SummonViewState> = MutableLiveData()
    val getViewState: LiveData<SummonViewState> = viewState
    private val recruitableUnits : MutableList<TransformerUIModel> = mutableListOf()

    fun fetchRecruitableUnits() {
        viewModelScope.launch {
            val result = getRecruitableTransformersUseCase.execute()
            recruitableUnits.apply {
                clear()
                addAll(
                    when(result) {
                        is TransformerResult.Success -> result.transformerList.map { it.toUIModel() }
                        is TransformerResult.Error -> {
                            listOf(Constants.defaultTransformer)
                        }
                    }
                )
            }
            viewState.postValue(SummonViewState.OnRecruitableTransformersReceived)
        }
    }

    fun registerTransformer(transformerUIModel: TransformerUIModel) {
        viewModelScope.launch {
            val result = try {
                registerTransformerUseCase.execute(transformerUIModel.toDomainModel())
                SummonViewState.OnUnitCreationSuccess(transformerUIModel.toSummonedUnitUIModel())
            } catch (ex: Exception) {
                SummonViewState.OnUnitCreationError("Unit Creation Error. Please Try Again")
            }
            viewState.postValue(result)
        }
    }

    fun getAvailableUnits() = recruitableUnits.toList()
}