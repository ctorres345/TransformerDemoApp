package com.pandaveloper.transformersdemo.presentation.ui.summon.dialog.customunit

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

class CustomUnitViewModel @ViewModelInject constructor() : BaseViewModel() {
    private val viewState: MutableLiveData<SingleEmitionEvent<CustomUnitViewState>> = MutableLiveData()
    val getViewState: LiveData<SingleEmitionEvent<CustomUnitViewState>> = viewState

    fun saveCustomUnit(name: String, team: UnitTeam?, type: UnitType?, customStats: UnitStatsUIModel? = null) {
        if(name.isEmpty()){
            viewState.value = SingleEmitionEvent(CustomUnitViewState.OnUnitNameError)
            return
        }
        if(team == null){
            viewState.value = SingleEmitionEvent(CustomUnitViewState.OnUnitTeamError)
            return
        }
        if(type == null){
            viewState.value = SingleEmitionEvent(CustomUnitViewState.OnUnitTypeError)
            return
        }
        if(type == UnitType.CUSTOM){
            if(customStats == null) return

            if(!customStats.hasValidStats()){
                validateCustomStats(customStats)
                return
            }
        }
        viewState.value = SingleEmitionEvent(CustomUnitViewState.OnUnitCreationSuccess(
            TransformerUIModel(
                name = name,
                team = team,
                unitStats = when(type){
                    UnitType.BASIC -> Constants.UnitTypeStats.BASIC
                    UnitType.FIGHTER -> Constants.UnitTypeStats.FIGTHER
                    UnitType.TACTICIAN -> Constants.UnitTypeStats.TACTICIAN
                    UnitType.ASSASSIN -> Constants.UnitTypeStats.ASSASSIN
                    UnitType.CUSTOM -> customStats!!
                }
            )
        ))
    }

    private fun validateCustomStats(customStats: UnitStatsUIModel) {
        if(!customStats.strength.isValidStat()){
            viewState.value = SingleEmitionEvent(CustomUnitViewState.OnUnitStrengthError)
            return
        }
        if(!customStats.intelligence.isValidStat()){
            viewState.value = SingleEmitionEvent(CustomUnitViewState.OnUnitIntelligenceError)
            return
        }
        if(!customStats.speed.isValidStat()){
            viewState.value = SingleEmitionEvent(CustomUnitViewState.OnUnitSpeedError)
            return
        }
        if(!customStats.endurance.isValidStat()){
            viewState.value = SingleEmitionEvent(CustomUnitViewState.OnUnitEnduranceError)
            return
        }
        if(!customStats.rank.isValidStat()){
            viewState.value = SingleEmitionEvent(CustomUnitViewState.OnUnitRankError)
            return
        }
        if(!customStats.courage.isValidStat()){
            viewState.value = SingleEmitionEvent(CustomUnitViewState.OnUnitCourageError)
            return
        }
        if(!customStats.firepower.isValidStat()){
            viewState.value = SingleEmitionEvent(CustomUnitViewState.OnUnitFirepowerError)
            return
        }
        if(!customStats.skill.isValidStat()){
            viewState.value = SingleEmitionEvent(CustomUnitViewState.OnUnitSkillError)
            return
        }
    }

    private fun Int.isValidStat() = this in 1..10
}