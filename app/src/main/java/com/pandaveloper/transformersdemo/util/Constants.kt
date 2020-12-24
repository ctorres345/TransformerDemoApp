package com.pandaveloper.transformersdemo.util

import com.pandaveloper.transformersdemo.enums.MenuOption
import com.pandaveloper.transformersdemo.enums.UnitTeam
import com.pandaveloper.transformersdemo.enums.UnitType
import com.pandaveloper.transformersdemo.model.MenuOptionUIModel
import com.pandaveloper.transformersdemo.model.TransformerUIModel
import com.pandaveloper.transformersdemo.model.UnitStatsUIModel

object Constants {
    object BundleParams {
        const val PARAM_UNIT = "unit"
        const val PARAM_BATTLE_RESULT = "battleResult"
    }

    val defaultTransformer = TransformerUIModel(
        name = "Errorbot",
        team = UnitTeam.AUTOBOT,
        unitStats = UnitStatsUIModel(
            strength = 1,
            intelligence = 1,
            speed = 1,
            endurance = 1,
            rank = 1,
            courage = 1,
            firepower = 1,
            skill = 1
        )
    )

    val unitTeams = listOf(UnitTeam.AUTOBOT, UnitTeam.DECEPTICON)

    val unitTypes = listOf(UnitType.ALLROUNDER, UnitType.TANK, UnitType.WARRIOR)

    val menuOptions = listOf(
        MenuOptionUIModel(
            displayName = "Inventory",
            option = MenuOption.INVENTORY
        ),
        MenuOptionUIModel(
            displayName = "Summon",
            option = MenuOption.SUMMON
        ),
        MenuOptionUIModel(
            displayName = "Battle",
            option = MenuOption.BATTLE
        ),
        MenuOptionUIModel(
            displayName = "Exit",
            option = MenuOption.EXIT
        )
    )

    object UnitTypeStats {
        val allRounder = UnitStatsUIModel(
            strength = 5,
            intelligence = 5,
            speed = 5,
            endurance = 5,
            rank = 5,
            courage = 5,
            firepower = 5,
            skill = 5
        )
        val warrior = UnitStatsUIModel(
            strength = 8,
            intelligence = 2,
            speed = 3,
            endurance = 5,
            rank = 5,
            courage = 7,
            firepower = 5,
            skill = 7
        )
        val tank = UnitStatsUIModel(
            strength = 5,
            intelligence = 7,
            speed = 2,
            endurance = 9,
            rank = 5,
            courage = 8,
            firepower = 4,
            skill = 5
        )
    }
}