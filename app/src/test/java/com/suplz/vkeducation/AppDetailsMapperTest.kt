package com.suplz.vkeducation

import com.suplz.vkeducation.data.appdetails.AppDetailsDto
import com.suplz.vkeducation.data.appdetails.AppDetailsMapper
import com.suplz.vkeducation.domain.Category
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class AppDetailsMapperTest {

    private lateinit var mapper: AppDetailsMapper

    @BeforeEach
    fun setUp() {
        mapper = AppDetailsMapper()
    }

    private fun createDto(
        id: String = "dto_99",
        name: String = "Network App",
        developer: String = "NetDev",
        category: Category = Category.SOCIAL,
        ageRating: Int = 18,
        size: Double = 999.99,
        icon: String = "url/dto_icon",
        screenshots: List<String>? = listOf("shot1", "shot2"),
        description: String = "Network desc"
    ) = AppDetailsDto(
        id = id,
        name = name,
        developer = developer,
        category = category,
        ageRating = ageRating,
        size = size,
        icon = icon,
        screenshots = screenshots,
        description = description
    )

    @Test
    fun `toDomain maps all fields and casts size to Float`() {
        val dto = createDto(size = 150.5)
        val domain = mapper.toDomain(dto)

        assertAll(
            { assertEquals(dto.id, domain.id) },
            { assertEquals(dto.name, domain.name) },
            { assertEquals(dto.developer, domain.developer) },
            { assertEquals(dto.category, domain.category) },
            { assertEquals(dto.ageRating, domain.ageRating) },
            { assertEquals(150.5f, domain.size) },
            { assertEquals(dto.icon, domain.iconUrl) },
            { assertEquals(dto.description, domain.description) }
        )
    }

    @Test
    fun `toDomain preserves null screenshots`() {
        val dto = createDto(screenshots = null)
        val domain = mapper.toDomain(dto)

        assertNull(domain.screenshotUrlList)
    }

    @Test
    fun `toDomain preserves non null screenshots list`() {
        val mockUrls = listOf("url1", "url2")
        val dto = createDto(screenshots = mockUrls)
        val domain = mapper.toDomain(dto)

        assertAll(
            { assertEquals(2, domain.screenshotUrlList?.size) },
            { assertEquals(mockUrls, domain.screenshotUrlList) }
        )
    }

    @Test
    fun `toDomain sets isInWishlist to false by default`() {
        val domain = mapper.toDomain(createDto())

        assertFalse(domain.isInWishlist)
    }
}