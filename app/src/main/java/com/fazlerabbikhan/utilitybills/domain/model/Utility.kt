package com.fazlerabbikhan.utilitybills.domain.model

data class Utility(
    val apis: String,
    val billable: Boolean,
    val code: String,
    val fields: List<Field>,
    val name: String,
    val name_bn: String
)