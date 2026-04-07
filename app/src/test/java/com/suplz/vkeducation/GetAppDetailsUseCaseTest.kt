package com.suplz.vkeducation

import com.suplz.vkeducation.domain.Category
import com.suplz.vkeducation.domain.appdetails.AppDetails
import com.suplz.vkeducation.domain.appdetails.AppDetailsRepository
import com.suplz.vkeducation.domain.appdetails.GetAppDetailsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class GetAppDetailsUseCaseTest {

    private val repository: AppDetailsRepository = mockk()
    private lateinit var useCase: GetAppDetailsUseCase

    @BeforeEach
    fun setUp() {
        useCase = GetAppDetailsUseCase(repository)
    }

    private fun createDomain(id: String = "target_app_id") = AppDetails(
        id = id,
        name = "Mock App",
        developer = "Mock Dev",
        category = Category.NEWS,
        ageRating = 12,
        size = 10f,
        iconUrl = "url",
        screenshotUrlList = emptyList(),
        description = "desc",
        isInWishlist = false
    )

    @Test
    fun `invoke should successfully return AppDetails from repository`() = runTest {
        val requestedId = "target_app_id"
        val expectedDomainData = createDomain(id = requestedId)
        coEvery { repository.get(requestedId) } returns expectedDomainData

        val result = useCase(requestedId)

        assertAll(
            { assertEquals(requestedId, result.id) },
            { assertEquals(expectedDomainData, result) }
        )
        coVerify(exactly = 1) { repository.get(requestedId) }
    }

    @Test
    fun `invoke should throw exception when repository fails`() = runTest {
        val requestedId = "error_id"
        val expectedException = RuntimeException("Network Error")
        coEvery { repository.get(requestedId) } throws expectedException

        val exception = assertThrows<RuntimeException> {
            useCase(requestedId)
        }

        assertEquals("Network Error", exception.message)
        coVerify(exactly = 1) { repository.get(requestedId) }
    }
}