package com.suplz.vkeducation.data.appdetails

import com.suplz.vkeducation.data.AppApi
import com.suplz.vkeducation.data.appdetails.local.AppDetailsDao
import com.suplz.vkeducation.data.appdetails.local.AppDetailsEntityMapper
import com.suplz.vkeducation.domain.appdetails.AppDetails
import com.suplz.vkeducation.domain.appdetails.AppDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor(
    private val appApi: AppApi,
    private val dao: AppDetailsDao,
    private val mapper: AppDetailsMapper,
    private val entityMapper: AppDetailsEntityMapper,
) : AppDetailsRepository {

    override suspend fun get(id: String): Flow<AppDetails> {
        return dao.getAppDetails(id).map { entity ->
            if (entity != null) {
                entityMapper.toDomain(entity)
            } else {
                val dto = appApi.getAppDetails(id)
                val domain = mapper.toDomain(dto)
                val newEntity = entityMapper.toEntity(domain)
                withContext(Dispatchers.IO) {
                    dao.insertAppDetails(newEntity)
                }
                domain
            }
        }
    }
}