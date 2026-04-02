package com.suplz.vkeducation.domain.appdetails

import javax.inject.Inject

class GetAppDetailsUseCase @Inject constructor(
    private val appDetailsRepository: AppDetailsRepository,
) {
    suspend operator fun invoke(id: String): AppDetails {
        return appDetailsRepository.get(id)
    }
}