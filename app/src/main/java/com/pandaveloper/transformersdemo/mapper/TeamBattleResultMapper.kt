package com.pandaveloper.transformersdemo.mapper

import com.pandaveloper.domain.model.result.TeamBattleResult
import com.pandaveloper.domain.utils.getNames
import com.pandaveloper.transformersdemo.enums.BattleResult
import com.pandaveloper.transformersdemo.model.BattleResultUIModel

fun TeamBattleResult.toUIModel() : BattleResultUIModel {
    return when(this){
        is TeamBattleResult.DecepticonWin -> BattleResultUIModel(
            winningUnitName = this.winner.name,
            losingTeamSurvivors = this.autobotSurvivors.getNames(),
            result = BattleResult.DECEPTICON
        )
        is TeamBattleResult.AutobotWin -> BattleResultUIModel(
            winningUnitName = this.winner.name,
            losingTeamSurvivors = this.decepticonSurvivors.getNames(),
            result = BattleResult.AUTOBOT
        )
        is TeamBattleResult.Tie -> BattleResultUIModel(
            result = BattleResult.TIE
        )
        is TeamBattleResult.Destroyed -> BattleResultUIModel(
            result = BattleResult.DESTROYED
        )
    }
}