package com.pandaveloper.transformersdemo.model
import com.pandaveloper.transformersdemo.enums.UnitTeam
import java.io.Serializable

data class SummonedUnitUIModel(
    val displayName: String,
    val displayRating: String,
    val displayStats: String,
    val team: UnitTeam
) : Serializable