package com.pandaveloper.domain.model.result

import com.pandaveloper.domain.model.Transformer

sealed class TeamBattleResult {
    data class AutobotWin(val rounds: Int, val winner: Transformer, val decepticonSurvivors: List<Transformer>) : TeamBattleResult()
    data class DecepticonWin(val rounds: Int, val winner: Transformer, val autobotSurvivors: List<Transformer>) : TeamBattleResult()
    object Tie : TeamBattleResult()
    object Destroyed : TeamBattleResult()
}