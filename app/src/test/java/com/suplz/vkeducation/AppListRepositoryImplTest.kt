package com.suplz.vkeducation

import com.suplz.vkeducation.data.AppApi
import com.suplz.vkeducation.data.applist.AppListRepositoryImpl
import com.suplz.vkeducation.data.applist.AppSummaryDto
import com.suplz.vkeducation.data.applist.AppSummaryMapper
import com.suplz.vkeducation.domain.Category
import com.suplz.vkeducation.domain.applist.AppSummary
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class AppListRepositoryImplTest {

    private val appApi: AppApi = mockk()
    private val mapper: AppSummaryMapper = mockk()
    private lateinit var repository: AppListRepositoryImpl

    @BeforeEach
    fun setUp() {
        repository = AppListRepositoryImpl(appApi, mapper)
    }

    private fun createDto(
        id: String = "dto_1",
        name: String = "App 1",
        category: Category = Category.GAME,
        iconUrl: String = "url1",
        description: String = "desc1"
    ) = AppSummaryDto(
        id = id,
        name = name,
        category = category,
        iconUrl = iconUrl,
        description = description
    )

    private fun createDomain(
        id: String = "dto_1",
        name: String = "App 1",
        category: Category = Category.GAME,
        iconUrl: String = "url1",
        description: String = "desc1"
    ) = AppSummary(
        id = id,
        name = name,
        category = category,
        iconUrl = iconUrl,
        description = description
    )

    @Test
    fun `get should return mapped list from api`() = runTest {
        val dto1 = createDto(id = "1", name = "First")
        val dto2 = createDto(id = "2", name = "Second")
        val domain1 = createDomain(id = "1", name = "First")
        val domain2 = createDomain(id = "2", name = "Second")

        val dtoList = listOf(dto1, dto2)
        val domainList = listOf(domain1, domain2)

        coEvery { appApi.getAppList() } returns dtoList
        every { mapper.toDomainList(dtoList) } returns domainList

        val result = repository.get()

        assertAll(
            { assertEquals(2, result.size) },
            { assertEquals("1", result[0].id) },
            { assertEquals("First", result[0].name) },
            { assertEquals("2", result[1].id) },
            { assertEquals(domainList, result) }
        )
        coVerify(exactly = 1) { appApi.getAppList() }
        verify(exactly = 1) { mapper.toDomainList(dtoList) }
    }

    @Test
    fun `get should return empty list when api returns empty`() = runTest {
        val emptyDtoList = emptyList<AppSummaryDto>()
        val emptyDomainList = emptyList<AppSummary>()

        coEvery { appApi.getAppList() } returns emptyDtoList
        every { mapper.toDomainList(emptyDtoList) } returns emptyDomainList

        val result = repository.get()

        assertTrue(result.isEmpty())
        coVerify(exactly = 1) { appApi.getAppList() }
        verify(exactly = 1) { mapper.toDomainList(emptyDtoList) }
    }

    @Test
    fun `get should propagate exception when api call fails`() = runTest {
        val expectedException = RuntimeException("API Error")
        coEvery { appApi.getAppList() } throws expectedException

        val exception = assertThrows<RuntimeException> {
            repository.get()
        }

        assertEquals("API Error", exception.message)
        coVerify(exactly = 1) { appApi.getAppList() }
        verify(exactly = 0) { mapper.toDomainList(any()) }
    }
}