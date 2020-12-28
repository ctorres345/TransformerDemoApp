package com.pandaveloper.transformersdemo.util

import com.pandaveloper.transformersdemo.model.TransformerUIModel
import com.pandaveloper.transformersdemo.model.UnitStatsUIModel


fun TransformerUIModel.calculateRating() : Int {
    return this.unitStats.calculateRating()
}

fun TransformerUIModel.calculateStars() : Int {
    return when(this.calculateRating()){
        in 0..10 -> 1
        in 10..20 -> 2
        in 20..30 -> 3
        in 30..40 -> 4
        else -> 5
    }
}

fun TransformerUIModel.updateFrom(updatedTransformer: TransformerUIModel) {
    this.team = updatedTransformer.team
    this.unitStats = updatedTransformer.unitStats
    this.name = updatedTransformer.name
}

fun UnitStatsUIModel.calculateRating() : Int {
    return this.strength + this.intelligence + this.speed + this.endurance + this.firepower
}

fun UnitStatsUIModel.hasValidStats() : Boolean {
    return (strength in 1..10 && intelligence in 1..10 && speed in 1..10 && endurance in 1..10 &&
            rank in 1..10 && courage in 1..10 && firepower in 1..10 && skill in 1..10)
}

fun TransformerUIModel.detailedStats() : String {
    val builder = StringBuilder()
    with(this.unitStats){
        builder.append("Strength = ${this.strength}\n")
        builder.append("Intelligence = ${this.intelligence}\n")
        builder.append("Speed = ${this.speed}\n")
        builder.append("Endurance = ${this.endurance}\n")
        builder.append("Rank = ${this.rank}\n")
        builder.append("Courage = ${this.courage}\n")
        builder.append("Firepower = ${this.firepower}\n")
        builder.append("Skill = ${this.skill}")
    }
    return builder.toString()
}