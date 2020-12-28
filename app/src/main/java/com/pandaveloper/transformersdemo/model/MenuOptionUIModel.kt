package com.pandaveloper.transformersdemo.model

import com.pandaveloper.transformersdemo.enums.MenuOption

/**
 * UI model for the options in the main menu
 * @param displayName Name of the option
 * @param option The option itself
 */
data class MenuOptionUIModel(
    val displayName: String,
    val option : MenuOption
)