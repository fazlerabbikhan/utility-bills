package com.fazlerabbikhan.utilitybills.data.remote.utility_dto

import com.fazlerabbikhan.utilitybills.domain.model.Field

data class FieldDto(
    val code: String,
    val config: Any,
    val default_value: Any,
    val label: String,
    val options: Any,
    val regex: String,
    val required: Boolean,
    val type: String
)

fun FieldDto.toField(): Field {
    return Field(
        code = code,
        config = config,
        default_value = default_value,
        label = label,
        options = options,
        regex = regex,
        required = required,
        type = type
    )
}