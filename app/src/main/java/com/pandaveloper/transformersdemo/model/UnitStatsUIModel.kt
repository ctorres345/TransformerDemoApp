package com.pandaveloper.transformersdemo.model

/**
 * This data class wraps the stats assigned to a Transformer in order to speed up the unit creation
 * and transformation
 *
 * @param strength Strength stat of the Transformer
 * @param intelligence Intelligence stat of the Transformer
 * @param speed Speed stat of the Transformer
 * @param endurance Endurance stat of the Transformer
 * @param rank Rank stat of the Transformer
 * @param courage Courage stat of the Transformer
 * @param firepower Firepower stat of the Transformer
 * @param skill Skill stat of the Transformer
 */
data class UnitStatsUIModel(
    var strength : Int = 1,
    var intelligence : Int = 1,
    var speed : Int = 1,
    var endurance : Int = 1,
    var rank : Int = 1,
    var courage : Int = 1,
    var firepower : Int = 1,
    var skill : Int = 1
)