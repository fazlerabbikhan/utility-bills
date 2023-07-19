package com.fazlerabbikhan.utilitybills.data.remote.utility_dto

import com.fazlerabbikhan.utilitybills.domain.model.UtilityType

data class UtilityTypeDto(
    val code: String,
    val name: String,
    val name_bn: String,
    val utilities: List<UtilityDto>
)

fun UtilityTypeDto.toUtilityType(): UtilityType {
    return UtilityType(
        code = code,
        name = name,
        name_bn = name_bn,
        utilities = utilities.map { it.toUtility() }
    )
}