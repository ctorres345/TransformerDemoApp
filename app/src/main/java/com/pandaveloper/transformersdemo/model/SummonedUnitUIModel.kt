package com.pandaveloper.transformersdemo.model
import com.pandaveloper.transformersdemo.enums.UnitTeam
import java.io.Serializable

/**
 * Ui model that wraps the data of a recruited transformer in order to speed up UI management of
 * this data.
 *
 * @param unitName Name of the recruited Unit
 * @param unitRating Overall Rating of the recruited Unit
 * @param unitStats Complete stats of the recruited Unit
 * @param unitStars Numbers of starts assigned to the unit. It depends on its overall rating
 * @param team Team of the recruited Unit, can be either Decepticon or Autobot
 */
data class SummonedUnitUIModel(
    val unitName: String,
    val unitRating: Int,
    val unitStats: String,
    val unitStars: Int,
    val team: UnitTeam
) : Serializable