package com.pandaveloper.data.mapper

import com.pandaveloper.data.model.TransformerDTO
import com.pandaveloper.data.model.TransformerRequestDTO
import com.pandaveloper.domain.model.Transformer


fun TransformerDTO.toDomainModel() : Transformer {
    return Transformer(
        id = this.id,
        name = this.name,
        team = this.team,
        strength = this.strength,
        intelligence = this.intelligence,
        speed = this.speed,
        endurance = this.endurance,
        rank = this.rank,
        courage = this.courage,
        firepower = this.firepower,
        skill = this.skill,
        teamIcon = this.teamIcon
    )
}

fun TransformerRequestDTO.toDomainModel() : Transformer {
    return Transformer(
        id = "0",
        name = this.name,
        team = this.team,
        strength = this.strength,
        intelligence = this.intelligence,
        speed = this.speed,
        endurance = this.endurance,
        rank = this.rank,
        courage = this.courage,
        firepower = this.firepower,
        skill = this.skill,
        teamIcon = ""
    )
}

fun Transformer.toDTO() : TransformerDTO {
    return TransformerDTO(
        id = this.id,
        name = this.name,
        team = this.team,
        strength = this.strength,
        intelligence = this.intelligence,
        speed = this.speed,
        endurance = this.endurance,
        rank = this.rank,
        courage = this.courage,
        firepower = this.firepower,
        skill = this.skill,
        teamIcon = this.teamIcon
    )
}

fun Transformer.toRequestDTO() : TransformerRequestDTO {
    return TransformerRequestDTO(
        id = this.id,
        name = this.name,
        team = this.team,
        strength = this.strength,
        intelligence = this.intelligence,
        speed = this.speed,
        endurance = this.endurance,
        rank = this.rank,
        courage = this.courage,
        firepower = this.firepower,
        skill = this.skill
    )
}