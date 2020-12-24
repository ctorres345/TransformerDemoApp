package com.pandaveloper.domain.utils

import com.pandaveloper.domain.model.Transformer
import java.lang.StringBuilder

fun Transformer.calculateRating() : Int {
    return this.strength + this.intelligence + this.speed + this.endurance + this.firepower
}

fun Transformer.isOptimus() : Boolean {
    return this.name == "Optimus Prime"
}

fun Transformer.isPredaking() : Boolean {
    return this.name == "Predaking"
}

fun Transformer.isAutobot() : Boolean {
    return this.team == "A"
}

fun Transformer.isDecepticon() : Boolean {
    return this.team == "D"
}

fun List<Transformer>.getNames() : String {
    val builder = StringBuilder()
    for (unit in this){
        builder.append("${unit.name}\n")
    }
    return builder.toString().trim()
}