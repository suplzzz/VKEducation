package com.suplz.vkeducation.data.appdetails

import com.suplz.vkeducation.data.AppApi
import com.suplz.vkeducation.data.appdetails.local.AppDetailsDao
import com.suplz.vkeducation.data.appdetails.local.AppDetailsEntityMapper
import com.suplz.vkeducation.domain.appdetails.AppDetails
import com.suplz.vkeducation.domain.appdetails.AppDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor(
    private val appApi: AppApi,
    private val dao: AppDetailsDao,
    private val mapper: AppDetailsMapper,
    private val entityMapper: AppDetailsEntityMapper,
) : AppDetailsRepository {

    override fun observeAppDetails(id: String): Flow<AppDetails> {
        return dao.getAppDetails(id)
            .mapNotNull { entity ->
                entity?.let { entityMapper.toDomain(it) }
            }
    }

    override suspend fun toggleWishlist(id: String) {
        val currentEntity = dao.getAppDetails(id).first()

        currentEntity?.let { entity ->
            withContext(Dispatchers.IO) {
                dao.updateWishlistStatus(id, !entity.isInWishlist)
            }
        }
    }

    override suspend fun get(id: String): AppDetails {
        val entity = dao.getAppDetails(id).first()

        return if (entity != null) {
            entityMapper.toDomain(entity)
        } else {
            val dto = appApi.getAppDetails(id)
            val domain = mapper.toDomain(dto)
            withContext(Dispatchers.IO) {
                dao.insertAppDetails(entityMapper.toEntity(domain))
            }
            domain
        }
    }
}