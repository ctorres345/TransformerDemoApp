package com.pandaveloper.transformersdemo.util

import com.pandaveloper.transformersdemo.enums.MenuOption
import com.pandaveloper.transformersdemo.enums.UnitTeam
import com.pandaveloper.transformersdemo.enums.UnitType
import com.pandaveloper.transformersdemo.model.MenuOptionUIModel
import com.pandaveloper.transformersdemo.model.TransformerUIModel
import com.pandaveloper.transformersdemo.model.UnitStatsUIModel

object Constants {
    const val DECEPTICON_IDENTIFIER = "D"
    const val AUTOBOT_IDENTIFIER = "A"
    val DEFAULT_UNIT = TransformerUIModel(
        name = "Errorbot",
        team = UnitTeam.AUTOBOT,
        unitStats = UnitTypeStats.BASIC
    )

    object Params {
        const val PARAM_UNIT = "unit"
        const val PARAM_BATTLE_RESULT = "battleResult"
    }

    object Collections {
        val UNIT_TEAMS = listOf(UnitTeam.AUTOBOT, UnitTeam.DECEPTICON)

        val UNIT_TYPES = listOf(UnitType.BASIC, UnitType.FIGHTER, UnitType.TACTICIAN, UnitType.ASSASSIN, UnitType.CUSTOM)

        val MENU_OPTIONS = listOf(
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
                displayName = "Exit Session",
                option = MenuOption.EXIT
            )
        )
    }

    object UnitTypeStats {
        val BASIC = UnitStatsUIModel()

        val FIGTHER = UnitStatsUIModel().apply {
            courage = 10
            strength = 10
        }

        val TACTICIAN = UnitStatsUIModel().apply {
            skill = 10
            intelligence = 10
        }

        val ASSASSIN = UnitStatsUIModel().apply {
            endurance = 10
            speed = 10
            rank = 10
            firepower = 10
        }
    }
}