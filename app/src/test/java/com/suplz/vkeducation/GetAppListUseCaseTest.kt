package com.suplz.vkeducation

import com.suplz.vkeducation.domain.Category
import com.suplz.vkeducation.domain.applist.AppListRepository
import com.suplz.vkeducation.domain.applist.AppSummary
import com.suplz.vkeducation.domain.applist.GetAppListUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class GetAppListUseCaseTest {

    private val repository: AppListRepository = mockk()
    private lateinit var useCase: GetAppListUseCase

    @BeforeEach
    fun setUp() {
        useCase = GetAppListUseCase(repository)
    }

    private fun createSummary(id: String, name: String) = AppSummary(
        id = id,
        name = name,
        category = Category.GAME,
        iconUrl = "url",
        description = "desc"
    )

    @Test
    fun `invoke should return complete mapped list from repository`() = runTest {
        val expectedList = listOf(
            createSummary("1", "App 1"),
            createSummary("2", "App 2")
        )
        coEvery { repository.get() } returns expectedList

        val result = useCase()

        assertAll(
            { assertEquals(2, result.size) },
            { assertEquals("1", result[0].id) },
            { assertEquals("2", result[1].id) },
            { assertEquals(expectedList, result) }
        )
        coVerify(exactly = 1) { repository.get() }
    }

    @Test
    fun `invoke should return empty list when repository data is empty`() = runTest {
        coEvery { repository.get() } returns emptyList()

        val result = useCase()

        assertTrue(result.isEmpty())
        coVerify(exactly = 1) { repository.get() }
    }
}