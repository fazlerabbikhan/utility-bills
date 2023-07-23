package com.fazlerabbikhan.utilitybills.data.remote

import com.fazlerabbikhan.utilitybills.data.remote.utility_dto.UtilityTypeDto
import retrofit2.http.GET

interface ApiService {

    @GET("/gp-utility/v1/gpay/utility-list")
    suspend fun getUtilityTypes(): List<UtilityTypeDto>
}