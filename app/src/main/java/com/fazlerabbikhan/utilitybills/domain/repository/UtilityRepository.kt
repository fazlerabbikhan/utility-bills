package com.fazlerabbikhan.utilitybills.domain.repository

import com.fazlerabbikhan.utilitybills.data.remote.utility_dto.UtilityTypeDto

interface UtilityRepository {

    suspend fun getUtilityTypes(): List<UtilityTypeDto>
}