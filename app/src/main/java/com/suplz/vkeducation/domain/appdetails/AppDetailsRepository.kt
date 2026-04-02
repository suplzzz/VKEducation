package com.suplz.vkeducation.domain.appdetails

interface AppDetailsRepository {
    suspend fun get(id: String): AppDetails
}