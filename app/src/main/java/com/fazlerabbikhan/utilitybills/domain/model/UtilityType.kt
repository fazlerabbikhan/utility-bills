package com.fazlerabbikhan.utilitybills.domain.model

data class UtilityType(
    val code: String,
    val name: String,
    val name_bn: String,
    val utilities: List<Utility>
)