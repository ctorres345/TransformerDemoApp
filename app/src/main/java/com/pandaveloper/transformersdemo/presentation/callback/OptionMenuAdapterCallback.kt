package com.pandaveloper.transformersdemo.presentation.callback

import com.pandaveloper.transformersdemo.model.MenuOptionUIModel

interface OptionMenuAdapterCallback {
    fun onOptionSelected(option: MenuOptionUIModel)
}