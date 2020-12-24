package com.pandaveloper.transformersdemo.mapper

import com.pandaveloper.domain.model.Transformer
import com.pandaveloper.transformersdemo.enums.UnitTeam
import com.pandaveloper.transformersdemo.model.SummonedUnitUIModel
import com.pandaveloper.transformersdemo.model.TransformerUIModel
import com.pandaveloper.transformersdemo.model.UnitStatsUIModel
import com.pandaveloper.transformersdemo.util.calculateRating
import com.pandaveloper.transformersdemo.util.calculateStars
import com.pandaveloper.transformersdemo.util.detailedStats

fun Transformer.toUIModel() : TransformerUIModel {
    return TransformerUIModel(
        id = this.id,
        name = this.name,
        team = if(this.team == "A") UnitTeam.AUTOBOT else UnitTeam.DECEPTICON,
        unitStats = UnitStatsUIModel(
            strength = this.strength,
            intelligence = this.intelligence,
            speed = this.speed,
            endurance = this.endurance,
            rank = this.rank,
            courage = this.courage,
            firepower = this.firepower,
            skill = this.skill
        ),
        teamIcon = this.teamIcon
    )
}

fun TransformerUIModel.toDomainModel() : Transformer {
    return Transformer(
        id = this.id,
        name = this.name,
        team = when(this.team){
            UnitTeam.AUTOBOT -> "A"
            UnitTeam.DECEPTICON -> "D"
        },
        strength = this.unitStats.strength,
        intelligence = this.unitStats.intelligence,
        speed = this.unitStats.speed,
        endurance = this.unitStats.endurance,
        rank = this.unitStats.rank,
        courage = this.unitStats.courage,
        firepower = this.unitStats.firepower,
        skill = this.unitStats.skill,
        teamIcon = this.teamIcon
    )
}

fun TransformerUIModel.toSummonedUnitUIModel() : SummonedUnitUIModel {
    return SummonedUnitUIModel(
        displayName = "${this.name} ${calculateStars()}",
        displayRating = "Overall Rating : ${this.calculateRating()}",
        displayStats = this.detailedStats(),
        team = this.team
    )
}