package com.pandaveloper.transformersdemo.model

import com.pandaveloper.transformersdemo.enums.UnitTeam
import java.io.Serializable

data class TransformerUIModel(
    var id : String = "0",
    var name : String,
    var team : UnitTeam,
    var unitStats: UnitStatsUIModel,
    val teamIcon : String? = null
) : Serializable

