package com.fazlerabbikhan.utilitybills.domain.model

data class Field(
    val code: String,
    val config: Any,
    val default_value: Any,
    val label: String,
    val options: Any,
    val regex: String,
    val required: Boolean,
    val type: String
)