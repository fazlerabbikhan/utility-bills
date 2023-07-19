package com.fazlerabbikhan.utilitybills.data.remote

import com.fazlerabbikhan.utilitybills.data.remote.utility_dto.UtilityTypeDto
import retrofit2.http.GET

interface ApiService {

    @GET("/gp-utility/gp/utility-list")
    suspend fun getUtilities(): UtilityTypeDto
}