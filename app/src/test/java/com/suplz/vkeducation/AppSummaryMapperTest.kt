package com.suplz.vkeducation

import com.suplz.vkeducation.data.applist.AppSummaryDto
import com.suplz.vkeducation.data.applist.AppSummaryMapper
import com.suplz.vkeducation.domain.Category
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class AppSummaryMapperTest {

    private lateinit var mapper: AppSummaryMapper

    @BeforeEach
    fun setUp() {
        mapper = AppSummaryMapper()
    }

    private fun createDto(
        id: String = "sum_1",
        name: String = "Finance Tracker",
        category: Category = Category.FINANCE,
        iconUrl: String = "icon_url",
        description: String = "desc"
    ) = AppSummaryDto(
        id = id,
        name = name,
        category = category,
        iconUrl = iconUrl,
        description = description
    )

    @Test
    fun `toDomain maps single AppSummaryDto correctly`() {
        val dto = createDto()
        val domain = mapper.toDomain(dto)

        assertAll(
            { assertEquals(dto.id, domain.id) },
            { assertEquals(dto.name, domain.name) },
            { assertEquals(dto.category, domain.category) },
            { assertEquals(dto.iconUrl, domain.iconUrl) },
            { assertEquals(dto.description, domain.description) }
        )
    }

    @Test
    fun `toDomainList maps populated list with correct order and fields`() {
        val dtoList = listOf(
            createDto(id = "1", name = "App 1", category = Category.GAME),
            createDto(id = "2", name = "App 2", category = Category.EDUCATION)
        )

        val domainList = mapper.toDomainList(dtoList)

        assertAll(
            { assertEquals(2, domainList.size) },
            { assertEquals("1", domainList[0].id) },
            { assertEquals("App 1", domainList[0].name) },
            { assertEquals(Category.GAME, domainList[0].category) },
            { assertEquals("2", domainList[1].id) },
            { assertEquals("App 2", domainList[1].name) },
            { assertEquals(Category.EDUCATION, domainList[1].category) }
        )
    }

    @Test
    fun `toDomainList returns empty list for empty input`() {
        val mappedEmptyList = mapper.toDomainList(emptyList())

        assertTrue(mappedEmptyList.isEmpty())
    }
}