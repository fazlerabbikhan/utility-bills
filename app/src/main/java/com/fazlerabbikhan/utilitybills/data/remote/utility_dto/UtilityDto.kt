package com.fazlerabbikhan.utilitybills.data.remote.utility_dto

import com.fazlerabbikhan.utilitybills.domain.model.Utility

data class UtilityDto(
    val apis: String,
    val billable: Boolean,
    val code: String,
    val fields: List<FieldDto>,
    val name: String,
    val name_bn: String
)

fun UtilityDto.toUtility(): Utility {
    return Utility(
        apis = apis,
        billable = billable,
        code = code,
        fields = fields.map { it.toField() },
        name = name,
        name_bn = name_bn
    )
}