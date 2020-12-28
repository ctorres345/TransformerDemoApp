package com.pandaveloper.transformersdemo.util

import com.pandaveloper.transformersdemo.enums.UnitTeam
import com.pandaveloper.transformersdemo.model.TransformerUIModel
import com.pandaveloper.transformersdemo.model.UnitStatsUIModel
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ModelExtTest {

    @Test
    fun `Given a UnitStats model with an invalid strength stat, then the hasValidStats extension returns false`(){
        val invalidStat1 = UnitStatsUIModel().apply { strength = 0 }
        val invalidStat2 = UnitStatsUIModel().apply { strength = 11 }
        assertFalse(invalidStat1.hasValidStats())
        assertFalse(invalidStat2.hasValidStats())
    }

    @Test
    fun `Given a UnitStats model with an invalid intelligence stat, then the hasValidStats extension returns false`(){
        val invalidStat1 = UnitStatsUIModel().apply { intelligence = 0 }
        val invalidStat2 = UnitStatsUIModel().apply { intelligence = 11 }
        assertFalse(invalidStat1.hasValidStats())
        assertFalse(invalidStat2.hasValidStats())
    }

    @Test
    fun `Given a UnitStats model with an invalid speed stat, then the hasValidStats extension returns false`(){
        val invalidStat1 = UnitStatsUIModel().apply { speed = 0 }
        val invalidStat2 = UnitStatsUIModel().apply { speed = 11 }
        assertFalse(invalidStat1.hasValidStats())
        assertFalse(invalidStat2.hasValidStats())
    }

    @Test
    fun `Given a UnitStats model with an invalid endurance stat, then the hasValidStats extension returns false`(){
        val invalidStat1 = UnitStatsUIModel().apply { endurance = 0 }
        val invalidStat2 = UnitStatsUIModel().apply { endurance = 11 }
        assertFalse(invalidStat1.hasValidStats())
        assertFalse(invalidStat2.hasValidStats())
    }

    @Test
    fun `Given a UnitStats model with an invalid rank stat, then the hasValidStats extension returns false`(){
        val invalidStat1 = UnitStatsUIModel().apply { rank = 0 }
        val invalidStat2 = UnitStatsUIModel().apply { rank = 11 }
        assertFalse(invalidStat1.hasValidStats())
        assertFalse(invalidStat2.hasValidStats())
    }

    @Test
    fun `Given a UnitStats model with an invalid courage stat, then the hasValidStats extension returns false`(){
        val invalidStat1 = UnitStatsUIModel().apply { courage = 0 }
        val invalidStat2 = UnitStatsUIModel().apply { courage = 11 }
        assertFalse(invalidStat1.hasValidStats())
        assertFalse(invalidStat2.hasValidStats())
    }

    @Test
    fun `Given a UnitStats model with an invalid firepower stat, then the hasValidStats extension returns false`(){
        val invalidStat1 = UnitStatsUIModel().apply { firepower = 0 }
        val invalidStat2 = UnitStatsUIModel().apply { firepower = 11 }
        assertFalse(invalidStat1.hasValidStats())
        assertFalse(invalidStat2.hasValidStats())
    }

    @Test
    fun `Given a UnitStats model with an invalid skill stat, then the hasValidStats extension returns false`(){
        val invalidStat1 = UnitStatsUIModel().apply { skill = 0 }
        val invalidStat2 = UnitStatsUIModel().apply { skill = 11 }
        assertFalse(invalidStat1.hasValidStats())
        assertFalse(invalidStat2.hasValidStats())
    }

    @Test
    fun `Given a UnitStats model with valid stats, then the hasValidStats extension returns true`(){
        val validStat = UnitStatsUIModel()
        assertTrue(validStat.hasValidStats())
    }

    /**
     * The formula for stat rating is the following
     * rating = strength + intelligence + speed + endurance + firepower
     */
    @Test
    fun `Given a UnitStats model with valid stats, the calculateRating extensions return the expected rating`() {
        val expectedRating = 5
        val validStats = UnitStatsUIModel() //All its stats are 1 when initialized
        assertTrue((expectedRating == validStats.calculateRating()))
    }

    /**
     * The formula for stat rating is the following
     * rating = strength + intelligence + speed + endurance + firepower
     */
    @Test
    fun `Given a TransformerUI model with valid stats, the calculateRating extensions return the expected rating`() {
        val expectedRating = 5
        val validUnit = TransformerUIModel(name = "test", team = UnitTeam.AUTOBOT) //All its stats are 1 when initialized
        assertTrue((expectedRating == validUnit.calculateRating()))
    }
}