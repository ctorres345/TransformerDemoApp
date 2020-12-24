package com.pandaveloper.testUtils

import com.pandaveloper.domain.model.Transformer

object TestHelper {
    fun createAutobot(name: String = "AutoTest") = createTransformer(name = name, team = "A")

    fun createDecepticon(name: String = "DeceptiTest") = createTransformer(name = name, team = "D")

    fun createOPAutobot(name: String = "AutoTest") = createTransformer(
        name = name,
        team = "A",
        strength = 10,
        intelligence = 10,
        speed = 10,
        endurance = 10,
        rank = 10,
        courage = 10,
        firepower = 10,
        skill = 10
    )

    fun createOPDecepticon(name: String = "Deceptitest") = createTransformer(
        name = name,
        team = "D",
        strength = 10,
        intelligence = 10,
        speed = 10,
        endurance = 10,
        rank = 10,
        courage = 10,
        firepower = 10,
        skill = 10
    )

    fun createTransformer(
        name: String = "AutoTest",
        team: String = "A",
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