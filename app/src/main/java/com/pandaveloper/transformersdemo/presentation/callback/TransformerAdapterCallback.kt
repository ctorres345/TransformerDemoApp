package com.pandaveloper.transformersdemo.presentation.callback

import com.pandaveloper.transformersdemo.model.TransformerUIModel

interface TransformerAdapterCallback {
    fun onTransformerSelected(transformer: TransformerUIModel)
    fun editTransformer(transformer: TransformerUIModel)
    fun deleteTransformer(transformerId: String)
}