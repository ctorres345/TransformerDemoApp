package com.pandaveloper.transformersdemo.model

import com.pandaveloper.transformersdemo.enums.BattleResult
import com.pandaveloper.transformersdemo.enums.UnitTeam
import java.io.Serializable

data class BattleResultUIModel(
    val winnerTeam: UnitTeam? = null,
    val winningUnitName: String? = null,
    val losingTeamSurvivors: String? = null,
    val result: BattleResult
) : Serializable