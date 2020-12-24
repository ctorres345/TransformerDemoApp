package com.pandaveloper.domain.usecase.impl

import com.pandaveloper.domain.model.Transformer
import com.pandaveloper.domain.model.result.SingleBattleResult
import com.pandaveloper.domain.model.result.TeamBattleResult
import com.pandaveloper.domain.usecase.TeamBattleResultUseCase
import com.pandaveloper.domain.utils.calculateRating
import com.pandaveloper.domain.utils.isAutobot
import com.pandaveloper.domain.utils.isDecepticon
import com.pandaveloper.domain.utils.isOptimus
import com.pandaveloper.domain.utils.isPredaking
import javax.inject.Inject
import kotlin.math.min

class TeamBattleResultUseCaseImpl @Inject constructor() : TeamBattleResultUseCase {

    override fun execute(
        autobotTeam: List<Transformer>,
        decepticonTeam: List<Transformer>
    ): TeamBattleResult {
        val autobotLineup = autobotTeam.sortedBy { it.rank }.toMutableList()
        val decepticonLineup = decepticonTeam.sortedBy { it.rank }.toMutableList()
        val totalRounds = min(autobotLineup.size, decepticonLineup.size)
        var roundCount = 1
        var finalResult: SingleBattleResult

        do {
            finalResult = performBattle(Pair(autobotLineup.first(), decepticonLineup.first()))
            when(finalResult) {
                is SingleBattleResult.AutobotWin -> decepticonLineup.removeFirst()
                is SingleBattleResult.DecepticonWin -> autobotLineup.removeFirst()
                is SingleBattleResult.Destroyed -> {
                    autobotLineup.clear()
                    decepticonLineup.clear()
                    continue
                }
                is SingleBattleResult.Tie -> {
                    decepticonLineup.removeFirst()
                    autobotLineup.removeFirst()
                }
            }
            roundCount++
        } while (roundCount < totalRounds)

        return evaluateFinalResult(
            finalResult = finalResult,
            originalAutobotLineup = autobotTeam,
            autobotSurvivors = autobotLineup,
            originalDecepticonLineup = decepticonTeam,
            decepticonSurvivors = decepticonLineup,
            rounds = roundCount
        )
    }

    private fun evaluateFinalResult(
        finalResult: SingleBattleResult,
        originalAutobotLineup: List<Transformer>,
        autobotSurvivors: List<Transformer>,
        originalDecepticonLineup: List<Transformer>,
        decepticonSurvivors: List<Transformer>,
        rounds: Int
    ) : TeamBattleResult{
        return when(finalResult) {
            is SingleBattleResult.Destroyed -> TeamBattleResult.Destroyed
            is SingleBattleResult.AutobotWin -> TeamBattleResult.AutobotWin(
                rounds = rounds,
                winner = autobotSurvivors.first(),
                decepticonSurvivors = decepticonSurvivors
            )
            is SingleBattleResult.DecepticonWin -> TeamBattleResult.DecepticonWin(
                rounds = rounds,
                winner = decepticonSurvivors.first(),
                autobotSurvivors = autobotSurvivors
            )
            is SingleBattleResult.Tie -> {
                val autobotDeathCounts = autobotSurvivors.size - originalAutobotLineup.size
                val decepticonDeathCounts = decepticonSurvivors.size - originalDecepticonLineup.size
                when {
                    autobotDeathCounts < decepticonDeathCounts -> TeamBattleResult.DecepticonWin(
                        rounds = rounds,
                        winner = decepticonSurvivors.first(),
                        autobotSurvivors = autobotSurvivors
                    )
                    decepticonDeathCounts < autobotDeathCounts -> TeamBattleResult.AutobotWin(
                        rounds = rounds,
                        winner = autobotSurvivors.first(),
                        decepticonSurvivors = decepticonSurvivors
                    )
                    else -> TeamBattleResult.Tie
                }
            }
        }
    }


    /**
     * This function will go through each of the rules to determine a winner
     */
    private fun performBattle(figthers: Pair<Transformer, Transformer>): SingleBattleResult {
        //First we evaluate the special rules
        val specialRuleResult = figthers.checkSpecialRule()
        if(specialRuleResult != SingleBattleResult.Tie) {
            return specialRuleResult
        }

        val match1Result = figthers.match1()
        //If its still a tie we begin with the first match
        if(match1Result != SingleBattleResult.Tie) {
            return match1Result
        }

        val match2Result = figthers.match2()
        //If its still a tie we continue with the second match
        if(match2Result != SingleBattleResult.Tie) {
            return match2Result
        }

        //If we don't have a winner yet just return the result of the 3rd match
        return figthers.match3()
    }

    /**
     * SPECIAL RULES
     * ●  Any Transformer named Optimus Prime or Predaking wins his fight automatically regardless of any other criteria
     * ●  In the event either of the above face each other (or a duplicate of each other), the game immediately ends with all competitors destroyed
     */
    private fun Pair<Transformer, Transformer>.checkSpecialRule() : SingleBattleResult {
        return when{
            first.isOptimus() && second.isPredaking() -> SingleBattleResult.Destroyed
            first.isOptimus() -> SingleBattleResult.AutobotWin
            second.isPredaking() -> SingleBattleResult.DecepticonWin
            else -> SingleBattleResult.Tie
        }
    }

    /**
     * RULES OF MATCH 1
     * If any fighter is down 4 or more points of courage and 3 or more points of strength
     * compared to their opponent, the opponent automatically wins the face-off regardless of
     * overall rating (opponent has ran away)
     */
    private fun Pair<Transformer, Transformer>.match1() : SingleBattleResult {
        return when{
            (first.courage - second.courage < -4) && (first.strength - second.strength < -3) && first.isAutobot() -> SingleBattleResult.DecepticonWin
            (second.courage - first.courage < -4) && (second.strength - first.strength < -3) && second.isDecepticon() -> SingleBattleResult.AutobotWin
            else -> SingleBattleResult.Tie
        }
    }

    /**
     * RULES OF MATCH 2
     * if one of the fighters is 3 or more points of skill above their opponent, they
     * win the fight regardless of overall rating
     */
    private fun Pair<Transformer, Transformer>.match2() : SingleBattleResult {
        return when{
            (first.skill - second.skill >= 3) && first.isAutobot() -> SingleBattleResult.AutobotWin
            (second.skill - first.skill >= 3) && second.isDecepticon() -> SingleBattleResult.DecepticonWin
            else -> SingleBattleResult.Tie
        }
    }

    /**
     * RULES OF MATCH 3
     * if one of the fighters is 3 or more points of skill above their opponent, they
     * win the fight regardless of overall rating
     */
    private fun Pair<Transformer, Transformer>.match3() : SingleBattleResult {
        return when{
            (first.calculateRating() > second.calculateRating()) && first.isAutobot() -> SingleBattleResult.AutobotWin
            (first.calculateRating() < second.calculateRating()) && second.isDecepticon() -> SingleBattleResult.DecepticonWin
            else -> SingleBattleResult.Tie
        }
    }

}