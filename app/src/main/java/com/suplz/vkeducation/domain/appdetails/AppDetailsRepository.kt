package com.suplz.vkeducation.domain.appdetails

import kotlinx.coroutines.flow.Flow

interface AppDetailsRepository {
    suspend fun get(id: String): Flow<AppDetails>
}