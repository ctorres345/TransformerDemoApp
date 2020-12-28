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
                validateCustomStats(customStats)
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

    private fun validateCustomStats(customStats: UnitStatsUIModel) {
        if(!customStats.strength.isValidStat()){
            viewState.value = SingleEmitionEvent(EditUnitViewState.OnUnitStrengthError)
            return
        }
        if(!customStats.intelligence.isValidStat()){
            viewState.value = SingleEmitionEvent(EditUnitViewState.OnUnitIntelligenceError)
            return
        }
        if(!customStats.speed.isValidStat()){
            viewState.value = SingleEmitionEvent(EditUnitViewState.OnUnitSpeedError)
            return
        }
        if(!customStats.endurance.isValidStat()){
            viewState.value = SingleEmitionEvent(EditUnitViewState.OnUnitEnduranceError)
            return
        }
        if(!customStats.rank.isValidStat()){
            viewState.value = SingleEmitionEvent(EditUnitViewState.OnUnitRankError)
            return
        }
        if(!customStats.courage.isValidStat()){
            viewState.value = SingleEmitionEvent(EditUnitViewState.OnUnitCourageError)
            return
        }
        if(!customStats.firepower.isValidStat()){
            viewState.value = SingleEmitionEvent(EditUnitViewState.OnUnitFirepowerError)
            return
        }
        if(!customStats.skill.isValidStat()){
            viewState.value = SingleEmitionEvent(EditUnitViewState.OnUnitSkillError)
            return
        }
    }

    private fun Int.isValidStat() = this in 1..10

}