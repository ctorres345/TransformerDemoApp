package com.pandaveloper.domain.testutils

import com.pandaveloper.domain.model.Transformer
import com.pandaveloper.domain.utils.Constants

object TestHelper {
    fun createOPAutobot() = createAutobot(
        strength = 10,
        intelligence = 10,
        speed = 10,
        endurance = 10,
        rank = 10,
        courage = 10,
        firepower = 10,
        skill = 10
    )

    fun createOPDecepticon() = createDecepticon(
        strength = 10,
        intelligence = 10,
        speed = 10,
        endurance = 10,
        rank = 10,
        courage = 10,
        firepower = 10,
        skill = 10
    )

    fun createDecepticon(
        name: String = "DecepticonTest",
        strength : Int = 1,
        intelligence : Int = 1,
        speed : Int = 1,
        endurance : Int = 1,
        rank : Int = 1,
        courage : Int = 1,
        firepower : Int = 1,
        skill : Int = 1
    ) = Transformer(
        id = "0",
        name = name,
        team = Constants.DECEPTICON_TEAM,
        strength = strength,
        intelligence = intelligence,
        speed = speed,
        endurance = endurance,
        rank = rank,
        courage = courage,
        firepower = firepower,
        skill = skill,
        teamIcon = ""
    )

    fun createAutobot(
        name: String = "AutobotTest",
        strength : Int = 1,
        intelligence : Int = 1,
        speed : Int = 1,
        endurance : Int = 1,
        rank : Int = 1,
        courage : Int = 1,
        firepower : Int = 1,
        skill : Int = 1
    ) = Transformer(
        id = "0",
        name = name,
        team = Constants.AUTOBOT_TEAM,
        strength = strength,
        intelligence = intelligence,
        speed = speed,
        endurance = endurance,
        rank = rank,
        courage = courage,
        firepower = firepower,
        skill = skill,
        teamIcon = ""
    )

    private fun createTransformer(
        name: String = "AutoTest",
        team: String = Constants.AUTOBOT_TEAM,
        strength : Int = 1,
        intelligence : Int = 1,
        speed : Int = 1,
        endurance : Int = 1,
        rank : Int = 1,
        courage : Int = 1,
        firepower : Int = 1,
        skill : Int = 1
    ) = Transformer(
        id = "0",
        name = name,
        team = team,
        strength = strength,
        intelligence = intelligence,
        speed = speed,
        endurance = endurance,
        rank = rank,
        courage = courage,
        firepower = firepower,
        skill = skill,
        teamIcon = ""
    )
}