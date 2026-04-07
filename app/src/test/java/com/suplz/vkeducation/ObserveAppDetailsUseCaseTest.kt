package com.suplz.vkeducation

import com.suplz.vkeducation.domain.Category
import com.suplz.vkeducation.domain.appdetails.AppDetails
import com.suplz.vkeducation.domain.appdetails.AppDetailsRepository
import com.suplz.vkeducation.domain.appdetails.ObserveAppDetailsUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ObserveAppDetailsUseCaseTest {

    private val repository: AppDetailsRepository = mockk()
    private lateinit var useCase: ObserveAppDetailsUseCase

    @BeforeEach
    fun setUp() {
        useCase = ObserveAppDetailsUseCase(repository)
    }

    private fun createDomain(id: String = "flow_id") = AppDetails(
        id = id,
        name = "Flow App",
        developer = "FlowDev",
        category = Category.MUSIC,
        ageRating = 12,
        size = 50f,
        iconUrl = "url",
        screenshotUrlList = listOf("img"),
        description = "desc",
        isInWishlist = true
    )

    @Test
    fun `invoke should return identical Flow from repository`() {
        val requestedId = "flow_id"
        val expectedDomainData = createDomain(id = requestedId)
        val mockFlow = flowOf(expectedDomainData)
        every { repository.observeAppDetails(requestedId) } returns mockFlow

        val resultFlow = useCase(requestedId)

        assertEquals(mockFlow, resultFlow)
        verify(exactly = 1) { repository.observeAppDetails(requestedId) }
    }
}