package com.suplz.vkeducation.domain.appdetails

import kotlinx.coroutines.flow.Flow

interface AppDetailsRepository {
    suspend fun get(id: String): AppDetails

    fun observeAppDetails(id: String): Flow<AppDetails>

    suspend fun toggleWishlist(id: String)
}