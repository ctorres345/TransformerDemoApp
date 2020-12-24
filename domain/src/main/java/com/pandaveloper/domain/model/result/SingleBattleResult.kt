package com.pandaveloper.domain.model.result

sealed class SingleBattleResult {
    object AutobotWin : SingleBattleResult()
    object DecepticonWin : SingleBattleResult()
    object Tie : SingleBattleResult()
    object Destroyed : SingleBattleResult()
}