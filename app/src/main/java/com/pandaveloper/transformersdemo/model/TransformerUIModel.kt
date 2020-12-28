package com.pandaveloper.transformersdemo.model

import com.pandaveloper.transformersdemo.enums.UnitTeam
import java.io.Serializable

/**
 * UI representation of a Transformer
 * @param id The id of the Transformer, in most cases will be null
 * @param name The name of the Transformer
 * @param team The team of the Transformer, can be either Decepticon or Autobot
 * @param unitStats The complete stats of the Transformer
 * @param teamIcon Team Icon for the transformer, can be null
 */
data class TransformerUIModel(
    var id : String = "0",
    var name : String,
    var team : UnitTeam,
    var unitStats: UnitStatsUIModel = UnitStatsUIModel(),
    var teamIcon : String? = null
) : Serializable

