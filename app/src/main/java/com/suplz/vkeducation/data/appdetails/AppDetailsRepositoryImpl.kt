package com.suplz.vkeducation.data.appdetails

import com.suplz.vkeducation.data.AppApi
import com.suplz.vkeducation.domain.appdetails.AppDetails
import com.suplz.vkeducation.domain.appdetails.AppDetailsRepository
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor(
            private val appApi : AppApi,
            private val mapper : AppDetailsMapper
) : AppDetailsRepository {

    override suspend fun get(id: String): AppDetails {
        val dto = appApi.getAppDetails(id)
        val domain = mapper.toDomain(dto)
        return domain
    }
}