package com.pandaveloper.domain.usecase.impl

import com.pandaveloper.domain.model.result.TeamBattleResult
import com.pandaveloper.testUtils.TestHelper
import org.junit.Assert
import org.junit.Test

class TeamBattleResultUseCaseImplTest {
    private val useCase = TeamBattleResultUseCaseImpl()

    @Test
    fun `Given a call to the use case with empty teams, then the use case will return a tie`() {
        //Result should be a tie
        val result = useCase.execute(emptyList(), emptyList())

        Assert.assertTrue(result is TeamBattleResult.Tie)
    }

    @Test
    fun `Given a fight between Optimus and any other decepticon, the use case will return a AutobotWin result due to the special rule`() {
        //Create Optimus Prime
        val autobotTeam = listOf(TestHelper.createAutobot("Optimus Prime"))
        //Create a basic decepticon
        val decepticonTeam = listOf(TestHelper.createDecepticon())
        //Result should be instawin for Autobot
        val result = useCase.execute(autobotTeam, decepticonTeam)

        Assert.assertTrue(result is TeamBattleResult.AutobotWin)
    }

    @Test
    fun `Given a fight between Predaking and any other autobot, the use case will return a DecepticonWin result due to the special rule`() {
        //Create basic autobot
        val autobotTeam = listOf(TestHelper.createAutobot())
        //Create Predaking
        val decepticonTeam = listOf(TestHelper.createDecepticon("Predaking"))
        //Result should be instawin for Decepticon
        val result = useCase.execute(autobotTeam, decepticonTeam)

        Assert.assertTrue(result is TeamBattleResult.DecepticonWin)
    }

    @Test
    fun `Given a fight between Optimus and Predaking, the use case will return a Destroyed result due to the special rule`() {
        //Create Optimus
        val autobotTeam = listOf(TestHelper.createAutobot("Optimus Prime"))
        //Create Predaking
        val decepticonTeam = listOf(TestHelper.createDecepticon("Predaking"))
        //Result should be Destroyed, no one wins
        val result = useCase.execute(autobotTeam, decepticonTeam)

        Assert.assertTrue(result is TeamBattleResult.Destroyed)
    }

    @Test
    fun `Given a fight between 2 similar transformers, the use case will return a Tie result due to the rules`() {
        //Create basic autobot
        val autobotTeam = listOf(TestHelper.createAutobot())
        //Create basic decepticon
        val decepticonTeam = listOf(TestHelper.createDecepticon())
        //Result should be a tie
        val result = useCase.execute(autobotTeam, decepticonTeam)

        Assert.assertTrue(result is TeamBattleResult.Tie)
    }

    /**
     * Match 1 Test
     *
     * If any fighter is down 4 or more points of courage and 3 or more points of strength
     * compared to their opponent, the opponent automatically wins the face-off regardless of
     * overall rating (opponent has ran away)
     */

    @Test
    fun `Given an autobot that wins match1, the use case will return an AutobotWin result due to the rules of match1`() {
        //Create an autobot with higher courage and strengh
        val autobotTeam = listOf(TestHelper.createTransformer(courage = 5, strength = 4))
        //Create basic decepticon
        val decepticonTeam = listOf(TestHelper.createDecepticon())
        //Result should be a win for autobot
        val result = useCase.execute(autobotTeam, decepticonTeam)

        Assert.assertTrue(result is TeamBattleResult.AutobotWin)
    }

    @Test
    fun `Given a decepticon that wins match1, the use case will return an DecepticonWin result due to the rules of match1`() {
        //Create a basic autobot
        val autobotTeam = listOf(TestHelper.createAutobot())
        //Create a decepticon with higher courage and strengh
        val decepticonTeam = listOf(TestHelper.createTransformer(team = "D", courage = 5, strength = 4))
        //Result should be a win for decepticon
        val result = useCase.execute(autobotTeam, decepticonTeam)

        Assert.assertTrue(result is TeamBattleResult.DecepticonWin)
    }

    /**
     * Match 2 Test
     *
     * if one of the fighters is 3 or more points of skill above their opponent, they
     * win the fight regardless of overall rating
     */

    @Test
    fun `Given an autobot that wins match2, the use case will return an AutobotWin result due to the rules of match2`() {
        //Create an autobot with hight skill
        val autobotTeam = listOf(TestHelper.createTransformer(skill = 4))
        //Create a decepticon with better rating but low skill. No courage nor strenght to bypass match1
        val decepticonTeam = listOf(TestHelper.createTransformer(
            team = "D",
            intelligence = 10,
            endurance = 10,
            speed = 10,
            rank = 10,
            firepower = 10
        ))
        //Result should be a win for autobot
        val result = useCase.execute(autobotTeam, decepticonTeam)

        Assert.assertTrue(result is TeamBattleResult.AutobotWin)
    }

    @Test
    fun `Given a decepticon that wins match2, the use case will return an DecepticonWin result due to the rules of match2`() {
        //Create an autobot with high rating but low skill.  No courage nor strenght to bypass match1
        val autobotTeam = listOf(TestHelper.createTransformer(
            intelligence = 10,
            endurance = 10,
            speed = 10,
            rank = 10,
            firepower = 10
        ))
        //Create a decepticon with high skill
        val decepticonTeam = listOf(TestHelper.createTransformer(team = "D", skill = 4))
        //Result should be a win for decepticon
        val result = useCase.execute(autobotTeam, decepticonTeam)

        Assert.assertTrue(result is TeamBattleResult.DecepticonWin)
    }

    /**
     * Match 3 Rules
     * The winner is the Transformer with the highest overall rating
     */

    @Test
    fun `Given an autobot that wins match3, the use case will return an AutobotWin result due to the rules of match3`() {
        //Create an autobot with high rating. No courage nor strenght nor skill to bypass match1 and 2
        val autobotTeam = listOf(TestHelper.createTransformer(
            intelligence = 10,
            endurance = 10,
            speed = 10,
            rank = 10,
            firepower = 10
        ))
        //Create basic decepticon
        val decepticonTeam = listOf(TestHelper.createDecepticon())
        //Result should be a win for autobot
        val result = useCase.execute(autobotTeam, decepticonTeam)

        Assert.assertTrue(result is TeamBattleResult.AutobotWin)
    }

    @Test
    fun `Given a decepticon that wins match3, the use case will return an DecepticonWin result due to the rules of match3`() {
        //Create a basic autobot
        val autobotTeam = listOf(TestHelper.createAutobot())
        //Create a decepticon with high rating. No courage nor strenght nor skill to bypass match1 and 2
        val decepticonTeam = listOf(TestHelper.createTransformer(
            team = "D",
            intelligence = 10,
            endurance = 10,
            speed = 10,
            rank = 10,
            firepower = 10
        ))
        //Result should be a win for decepticon
        val result = useCase.execute(autobotTeam, decepticonTeam)

        Assert.assertTrue(result is TeamBattleResult.DecepticonWin)
    }


    @Test
    fun `Given a battle of 2 vs 1 any Transformers who don’t have a fight are skipped`() {
        //Create a team of 2 autobots
        val autobotTeam = listOf(TestHelper.createAutobot(), TestHelper.createAutobot())
        //Create 1 op decepticon
        val decepticonTeam = listOf(TestHelper.createOPDecepticon())
        //Result should be a win for decepticon with 1 survivor for the autobots
        val result = useCase.execute(autobotTeam, decepticonTeam)

        Assert.assertTrue(result is TeamBattleResult.DecepticonWin)
        Assert.assertTrue((result as TeamBattleResult.DecepticonWin).autobotSurvivors.size == 1)
    }

}