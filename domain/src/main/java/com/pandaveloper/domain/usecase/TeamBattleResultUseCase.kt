package com.pandaveloper.domain.usecase

import com.pandaveloper.domain.model.Transformer
import com.pandaveloper.domain.model.result.TeamBattleResult

/**
 * This use case takes care of all the fight process in the battle screen.
 * It takes care of making sure the following rules retrieve the expected results
 *
 * The basic rules of the battle are:
 * ● The teams should be sorted by rank and faced off one on one against each other in order to determine a victor, the loser is eliminated
 * ● A battle between opponents uses the following rules:
 * ○ If any fighter is down 4 or more points of courage and 3 or more points of strength
 *   compared to their opponent, the opponent automatically wins the face-off regardless of
 *   overall rating (opponent has ran away)
 * ○ Otherwise, if one of the fighters is 3 or more points of skill above their opponent, they
 *  win the fight regardless of overall rating
 * ○ The winner is the Transformer with the highest overall rating
 * ● In the event of a tie, both Transformers are considered destroyed
 * ● Any Transformers who don’t have a fight are skipped (i.e. if it’s a team of 2 vs. a team of 1,
 * there’s only going to be one battle)
 * ● The team who eliminated the largest number of the opposing team is the winner
 *
 * Special rules:
 * ● Any Transformer named Optimus Prime or Predaking wins his fight automatically regardless of any other criteria
 * ● In the event either of the above face each other (or a duplicate of each other), the game immediately ends with all competitors destroyed
 *
 */
interface TeamBattleResultUseCase {
    fun execute(autobotTeam: List<Transformer>, decepticonTeam: List<Transformer>) : TeamBattleResult
}