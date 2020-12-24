package com.pandaveloper.data.model

import com.google.gson.annotations.SerializedName

data class ResponseDTO(
    @SerializedName("transformers") val result: List<TransformerDTO>
)