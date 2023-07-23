package com.fazlerabbikhan.utilitybills.data.repository

import com.fazlerabbikhan.utilitybills.data.remote.ApiService
import com.fazlerabbikhan.utilitybills.data.remote.utility_dto.UtilityTypeDto
import com.fazlerabbikhan.utilitybills.domain.repository.UtilityRepository
import javax.inject.Inject

class UtilityRepositoryImpl @Inject constructor(
    private val api: ApiService
) : UtilityRepository {

    override suspend fun getUtilityTypes(): List<UtilityTypeDto> {
        return api.getUtilityTypes()
    }
}