package com.fazlerabbikhan.utilitybills.domain.use_case

import com.fazlerabbikhan.utilitybills.common.Resource
import com.fazlerabbikhan.utilitybills.data.remote.utility_dto.toUtilityType
import com.fazlerabbikhan.utilitybills.domain.model.UtilityType
import com.fazlerabbikhan.utilitybills.domain.repository.UtilityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUtilitiesUseCase @Inject constructor(
    private val repository: UtilityRepository
) {
    operator fun invoke(): Flow<Resource<UtilityType>> = flow {
        try {
            emit(Resource.Loading<UtilityType>())
            val utilities = repository.getUtilities().toUtilityType()
            emit(Resource.Success<UtilityType>(utilities))
        } catch(e: HttpException) {
            emit(Resource.Error<UtilityType>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error<UtilityType>("Couldn't reach server. Check your internet connection."))
        }
    }
}