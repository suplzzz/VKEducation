package com.suplz.vkeducation.domain.appdetails

import com.suplz.vkeducation.domain.Category

class GetAppDetailsUseCase(
    private val appDetailsRepository: AppDetailsRepository,
) {
    suspend operator fun invoke(): AppDetails {
        val app: AppDetails = appDetailsRepository.get()

        if (app.category == Category.GAME) {
            throw IllegalStateException()
        }

        return app
    }
}