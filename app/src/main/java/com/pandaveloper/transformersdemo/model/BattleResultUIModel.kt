package com.pandaveloper.transformersdemo.model

import com.pandaveloper.transformersdemo.enums.BattleResult
import java.io.Serializable

/**
 * This data class will hold the results of a Battle between 2 transformers in order
 * to speed up UI management of this data
 * @param winningUnitName The name of the unit that won the Battle
 * @param losingTeamSurvivors The names of the survivors from the opossing team
 * @param result The final result of the battle (Tie, AutobotWin, etc...)
 */
data class BattleResultUIModel(
    val winningUnitName: String? = null,
    val losingTeamSurvivors: String? = null,
    val result: BattleResult
) : Serializable