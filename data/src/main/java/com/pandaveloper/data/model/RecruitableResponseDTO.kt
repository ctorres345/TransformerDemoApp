package com.pandaveloper.data.model

import com.google.gson.annotations.SerializedName

data class RecruitableResponseDTO(
    @SerializedName("transformers") val result: List<TransformerRequestDTO>
)