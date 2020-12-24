package com.pandaveloper.domain.model.result

import com.pandaveloper.domain.model.Transformer

sealed class TeamValidationResult {
    data class Success(val autobots: List<Transformer>, val decepticons: List<Transformer>) : TeamValidationResult()
    object NoUnitsError : TeamValidationResult()
    object NotEnoughAutobotsError : TeamValidationResult()
    object NotEnoughDecepticonsError : TeamValidationResult()
}