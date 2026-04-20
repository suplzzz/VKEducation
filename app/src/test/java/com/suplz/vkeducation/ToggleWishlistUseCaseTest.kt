package com.suplz.vkeducation

import com.suplz.vkeducation.domain.appdetails.AppDetailsRepository
import com.suplz.vkeducation.domain.appdetails.ToggleWishlistUseCase
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ToggleWishlistUseCaseTest {

    private val repository: AppDetailsRepository = mockk()
    private lateinit var useCase: ToggleWishlistUseCase

    @BeforeEach
    fun setUp() {
        useCase = ToggleWishlistUseCase(repository)
    }

    @Test
    fun `invoke should successfully trigger toggle in repository`() = runTest {
        val targetId = "wishlist_id"
        coEvery { repository.toggleWishlist(targetId) } just Runs

        useCase(targetId)

        coVerify(exactly = 1) { repository.toggleWishlist(targetId) }
    }
}