package com.pandaveloper.transformersdemo.enums

/**
 * The UnitType enum is used to wrap a certain set of stats in order to speed up the unit creation
 * form. The detailed stats per unit is the following.
 *
 * BASIC -> (All stats on 1)
 * FIGHTER -> (Courage and Strength on 10, all the others on 1)
 * TACTICIAN -> (Skill and Intelligence on 10, all the others on 1)
 * ASSASSIN -> (Endurance, Speed, Rank and Firepower on 10, all the others on 1)
 *
 * The Custom type is used to identify a unit that will have custom stats
 */
enum class UnitType {
    BASIC,
    FIGHTER,
    TACTICIAN,
    ASSASSIN,
    CUSTOM
}