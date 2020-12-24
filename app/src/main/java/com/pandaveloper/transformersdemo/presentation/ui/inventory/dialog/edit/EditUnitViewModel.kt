package com.pandaveloper.transformersdemo.presentation.ui.inventory.dialog.edit

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pandaveloper.transformersdemo.enums.UnitTeam
import com.pandaveloper.transformersdemo.enums.UnitType
import com.pandaveloper.transformersdemo.model.TransformerUIModel
import com.pandaveloper.transformersdemo.presentation.base.BaseViewModel
import com.pandaveloper.transformersdemo.util.Constants
import com.pandaveloper.transformersdemo.util.SingleEmitionEvent

class EditUnitViewModel @ViewModelInject constructor() : BaseViewModel() {
    private val viewState: MutableLiveData<SingleEmitionEvent<EditUnitViewState>> = MutableLiveData()
    val getViewState: LiveData<SingleEmitionEvent<EditUnitViewState>> = viewState

    fun updateUnit(originalUnit: TransformerUIModel, name: String, team: UnitTeam?, type: UnitType?) {
        if(name.isEmpty()){
            viewState.value = SingleEmitionEvent(EditUnitViewState.OnUnitNameError("Please insert a valid name for your unit"))
            return
        }
        if(team == null){
            viewState.value = SingleEmitionEvent(EditUnitViewState.OnUnitTeamError("Please select a valid team for your unit"))
            return
        }
        if(type == null){
            viewState.value = SingleEmitionEvent(EditUnitViewState.OnUnitTypeError("Please select a valid type for your unit"))
            return
        }
        viewState.value = SingleEmitionEvent(EditUnitViewState.OnUnitUpdated(
            originalUnit.apply {
                this.name = name
                this.team = team
                unitStats = when(type){
                    UnitType.ALLROUNDER -> Constants.UnitTypeStats.allRounder
                    UnitType.WARRIOR -> Constants.UnitTypeStats.warrior
                    UnitType.TANK -> Constants.UnitTypeStats.tank
                }
            }
        ))
    }

}