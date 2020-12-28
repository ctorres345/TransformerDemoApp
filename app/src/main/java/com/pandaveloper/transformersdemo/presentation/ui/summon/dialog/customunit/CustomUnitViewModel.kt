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
                validateCustomStats(customStats)?.let {
                    viewState.value = SingleEmitionEvent(it)
                }
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

    private fun validateCustomStats(customStats: UnitStatsUIModel) : CustomUnitViewState? {
        return when {
            !customStats.strength.isValidStat() -> CustomUnitViewState.OnUnitStrengthError
            !customStats.intelligence.isValidStat() -> CustomUnitViewState.OnUnitIntelligenceError
            !customStats.speed.isValidStat() -> CustomUnitViewState.OnUnitSpeedError
            !customStats.endurance.isValidStat() -> CustomUnitViewState.OnUnitEnduranceError
            !customStats.rank.isValidStat() -> CustomUnitViewState.OnUnitRankError
            !customStats.courage.isValidStat() -> CustomUnitViewState.OnUnitCourageError
            !customStats.firepower.isValidStat() -> CustomUnitViewState.OnUnitFirepowerError
            !customStats.skill.isValidStat() -> CustomUnitViewState.OnUnitSkillError
            else -> null
        }
    }

    private fun Int.isValidStat() = this in 1..10
}