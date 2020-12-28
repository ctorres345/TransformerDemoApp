package com.pandaveloper.transformersdemo.presentation.ui.inventory.dialog.edit

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pandaveloper.transformersdemo.enums.UnitTeam
import com.pandaveloper.transformersdemo.enums.UnitType
import com.pandaveloper.transformersdemo.model.TransformerUIModel
import com.pandaveloper.transformersdemo.model.UnitStatsUIModel
import com.pandaveloper.transformersdemo.presentation.base.BaseViewModel
import com.pandaveloper.transformersdemo.util.Constants
import com.pandaveloper.transformersdemo.util.SingleEmitionEvent
import com.pandaveloper.transformersdemo.util.hasValidStats

class EditUnitViewModel @ViewModelInject constructor() : BaseViewModel() {
    private val viewState: MutableLiveData<SingleEmitionEvent<EditUnitViewState>> = MutableLiveData()
    val getViewState: LiveData<SingleEmitionEvent<EditUnitViewState>> = viewState

    fun updateUnit(originalUnit: TransformerUIModel, name: String, team: UnitTeam?, type: UnitType?, customStats: UnitStatsUIModel? = null) {
        if(name.isEmpty()){
            viewState.value = SingleEmitionEvent(EditUnitViewState.OnUnitNameError)
            return
        }
        if(team == null){
            viewState.value = SingleEmitionEvent(EditUnitViewState.OnUnitTeamError)
            return
        }
        if(type == null){
            viewState.value = SingleEmitionEvent(EditUnitViewState.OnUnitTypeError)
            return
        }
        if(type == UnitType.CUSTOM){
            if(customStats == null) return

            if(!customStats.hasValidStats()){
                validateCustomStats(customStats)?.let {
                    viewState.value = SingleEmitionEvent(it)
                }
                return
            }
        }
        viewState.value = SingleEmitionEvent(EditUnitViewState.OnUnitUpdated(
            originalUnit.apply {
                this.name = name
                this.team = team
                unitStats = when(type){
                    UnitType.BASIC -> Constants.UnitTypeStats.BASIC
                    UnitType.FIGHTER -> Constants.UnitTypeStats.FIGTHER
                    UnitType.TACTICIAN -> Constants.UnitTypeStats.TACTICIAN
                    UnitType.ASSASSIN -> Constants.UnitTypeStats.ASSASSIN
                    UnitType.CUSTOM -> customStats!!
                }
            }
        ))
    }

    private fun validateCustomStats(customStats: UnitStatsUIModel) : EditUnitViewState? {
        return when {
            !customStats.strength.isValidStat() -> EditUnitViewState.OnUnitStrengthError
            !customStats.intelligence.isValidStat() -> EditUnitViewState.OnUnitIntelligenceError
            !customStats.speed.isValidStat() -> EditUnitViewState.OnUnitSpeedError
            !customStats.endurance.isValidStat() -> EditUnitViewState.OnUnitEnduranceError
            !customStats.rank.isValidStat() -> EditUnitViewState.OnUnitRankError
            !customStats.courage.isValidStat() -> EditUnitViewState.OnUnitCourageError
            !customStats.firepower.isValidStat() -> EditUnitViewState.OnUnitFirepowerError
            !customStats.skill.isValidStat() -> EditUnitViewState.OnUnitSkillError
            else -> null
        }
    }

    private fun Int.isValidStat() = this in 1..10
}