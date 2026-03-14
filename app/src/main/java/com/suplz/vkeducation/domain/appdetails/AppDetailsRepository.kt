package com.suplz.vkeducation.domain.appdetails

interface AppDetailsRepository {
    suspend fun get(): AppDetails
}