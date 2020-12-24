package com.pandaveloper.domain.utils

import com.pandaveloper.domain.model.Transformer
import java.lang.StringBuilder

fun Transformer.calculateRating() : Int {
    return this.strength + this.intelligence + this.speed + this.endurance + this.firepower
}

fun Transformer.isOptimus() : Boolean {
    return this.name == Constants.OPTIMUS
}

fun Transformer.isPredaking() : Boolean {
    return this.name == Constants.PREDAKING
}

fun Transformer.isAutobot() : Boolean {
    return this.team == Constants.AUTOBOT_TEAM
}

fun Transformer.isDecepticon() : Boolean {
    return this.team == Constants.DECEPTICON_TEAM
}

fun List<Transformer>.getNames() : String {
    val builder = StringBuilder()
    for (unit in this){
        builder.append("${unit.name}\n")
    }
    return builder.toString().trim()
}