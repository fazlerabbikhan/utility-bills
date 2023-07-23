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

class GetUtilityTypesUseCase @Inject constructor(
    private val repository: UtilityRepository
) {
    operator fun invoke(): Flow<Resource<List<UtilityType>>> = flow {
        try {
            emit(Resource.Loading<List<UtilityType>>())
            val utilityTypes = repository.getUtilityTypes().map { it.toUtilityType() }
            emit(Resource.Success<List<UtilityType>>(utilityTypes))
        } catch(e: HttpException) {
            emit(Resource.Error<List<UtilityType>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error<List<UtilityType>>("Couldn't reach server. Check your internet connection."))
        }
    }
}