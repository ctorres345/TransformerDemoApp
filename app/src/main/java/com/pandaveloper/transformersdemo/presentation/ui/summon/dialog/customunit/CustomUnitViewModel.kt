package com.pandaveloper.transformersdemo.presentation.ui.summon.dialog.customunit

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pandaveloper.transformersdemo.enums.UnitTeam
import com.pandaveloper.transformersdemo.enums.UnitType
import com.pandaveloper.transformersdemo.model.TransformerUIModel
import com.pandaveloper.transformersdemo.presentation.base.BaseViewModel
import com.pandaveloper.transformersdemo.util.Constants

class CustomUnitViewModel @ViewModelInject constructor() : BaseViewModel() {
    private val viewState: MutableLiveData<CustomUnitViewState> = MutableLiveData()
    val getViewState: LiveData<CustomUnitViewState> = viewState

    fun saveCustomUnit(name: String, team: UnitTeam?, type: UnitType?) {
        if(name.isEmpty()){
            viewState.value = CustomUnitViewState.OnUnitNameError("Please insert a valid name for your unit")
            return
        }
        if(team == null){
            viewState.value = CustomUnitViewState.OnUnitTeamError("Please select a valid team for your unit")
            return
        }
        if(type == null){
            viewState.value = CustomUnitViewState.OnUnitTypeError("Please select a valid type for your unit")
            return
        }
        viewState.value = CustomUnitViewState.OnUnitCreationSuccess(
            TransformerUIModel(
                name = name,
                team = team,
                unitStats = when(type){
                    UnitType.ALLROUNDER -> Constants.UnitTypeStats.allRounder
                    UnitType.WARRIOR -> Constants.UnitTypeStats.warrior
                    UnitType.TANK -> Constants.UnitTypeStats.tank
                }
            )
        )
    }

}