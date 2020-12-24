package com.pandaveloper.transformersdemo.presentation.ui.versus

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pandaveloper.domain.model.Transformer
import com.pandaveloper.domain.model.result.TeamValidationResult
import com.pandaveloper.domain.usecase.TeamBattleResultUseCase
import com.pandaveloper.domain.usecase.ValidateVersusTeamsUseCase
import com.pandaveloper.domain.utils.getNames
import com.pandaveloper.transformersdemo.mapper.toUIModel
import com.pandaveloper.transformersdemo.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class VersusViewModel @ViewModelInject constructor(
    private val teamValidationUseCase: ValidateVersusTeamsUseCase,
    private val teamBattleResultUseCase: TeamBattleResultUseCase
) : BaseViewModel() {
    private val viewState: MutableLiveData<VersusViewState> = MutableLiveData()
    val getViewState: LiveData<VersusViewState> = viewState
    private val autobotTeam : MutableList<Transformer> = mutableListOf()
    private val decepticonTeam : MutableList<Transformer> = mutableListOf()

    fun validateTeams() {
        viewModelScope.launch {
            val validationResult = teamValidationUseCase.execute()
            viewState.postValue(
                when(validationResult){
                    is TeamValidationResult.Success -> {
                        autobotTeam.addAll(validationResult.autobots)
                        decepticonTeam.addAll(validationResult.decepticons)
                        VersusViewState.TeamValidationSuccess
                    }
                    is TeamValidationResult.NotEnoughDecepticonsError -> VersusViewState.NoDecepticonsError
                    is TeamValidationResult.NotEnoughAutobotsError -> VersusViewState.NoAutobotsError
                    is TeamValidationResult.NoUnitsError -> VersusViewState.NoUnitsError
                }
            )
        }
    }

    fun performBattle() {
        if(autobotTeam.isEmpty() || decepticonTeam.isEmpty()) return

        viewModelScope.launch {
            val battleResult = teamBattleResultUseCase.execute(autobotTeam, decepticonTeam)
            viewState.postValue(VersusViewState.OnBattleResults(battleResult.toUIModel()))
        }
    }

    fun getAutobotLineup() = autobotTeam.getNames()
    fun getDecepticonLineup() = decepticonTeam.getNames()
}