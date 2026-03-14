package com.suplz.vkeducation.data.appdetails

import com.suplz.vkeducation.data.AppApi
import com.suplz.vkeducation.domain.appdetails.AppDetails
import com.suplz.vkeducation.domain.appdetails.AppDetailsRepository

class AppDetailsRepositorImpl : AppDetailsRepository {
    private val appApi = AppApi()
    private val mapper = AppDetailsMapper()

    override suspend fun get(): AppDetails {
        val dto = appApi.get()
        val domain = mapper.toDomain(dto)
        return domain
    }
}