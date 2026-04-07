package com.suplz.vkeducation

import com.suplz.vkeducation.data.appdetails.local.AppDetailsEntity
import com.suplz.vkeducation.data.appdetails.local.AppDetailsEntityMapper
import com.suplz.vkeducation.domain.Category
import com.suplz.vkeducation.domain.appdetails.AppDetails
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class AppDetailsEntityMapperTest {

    private lateinit var mapper: AppDetailsEntityMapper

    @BeforeEach
    fun setUp() {
        mapper = AppDetailsEntityMapper()
    }

    private fun createDomain(
        id: String = "app_123",
        name: String = "Test App",
        developer: String = "VK",
        category: Category = Category.GAME,
        ageRating: Int = 16,
        size: Float = 1024.5f,
        iconUrl: String = "https://icon.com",
        screenshotUrlList: List<String>? = listOf("img1", "img2"),
        description: String = "A great game",
        isInWishlist: Boolean = true
    ) = AppDetails(
        id = id,
        name = name,
        developer = developer,
        category = category,
        ageRating = ageRating,
        size = size,
        iconUrl = iconUrl,
        screenshotUrlList = screenshotUrlList,
        description = description,
        isInWishlist = isInWishlist
    )

    private fun createEntity(
        id: String = "db_777",
        name: String = "Utility App",
        developer: String = "Tools Inc",
        category: Category = Category.UTILITIES,
        ageRating: Int = 3,
        size: Float = 45.0f,
        iconUrl: String = "url/icon",
        screenshots: String? = "old_data",
        description: String = "Cleans your phone",
        isInWishlist: Boolean = false
    ) = AppDetailsEntity(
        id = id,
        name = name,
        developer = developer,
        category = category,
        ageRating = ageRating,
        size = size,
        iconUrl = iconUrl,
        screenshots = screenshots,
        description = description,
        isInWishlist = isInWishlist
    )

    @Test
    fun `toEntity maps all basic fields exactly`() {
        val domain = createDomain()
        val entity = mapper.toEntity(domain)

        assertAll(
            { assertEquals(domain.id, entity.id) },
            { assertEquals(domain.name, entity.name) },
            { assertEquals(domain.developer, entity.developer) },
            { assertEquals(domain.category, entity.category) },
            { assertEquals(domain.ageRating, entity.ageRating) },
            { assertEquals(domain.size, entity.size) },
            { assertEquals(domain.iconUrl, entity.iconUrl) },
            { assertEquals(domain.description, entity.description) },
            { assertEquals(domain.isInWishlist, entity.isInWishlist) }
        )
    }

    @Test
    fun `toEntity strictly sets screenshots to null`() {
        val domain = createDomain(screenshotUrlList = listOf("url1", "url2"))
        val entity = mapper.toEntity(domain)

        assertNull(entity.screenshots)
    }

    @Test
    fun `toDomain maps all basic database fields accurately`() {
        val entity = createEntity()
        val domain = mapper.toDomain(entity)

        assertAll(
            { assertEquals(entity.id, domain.id) },
            { assertEquals(entity.name, domain.name) },
            { assertEquals(entity.developer, domain.developer) },
            { assertEquals(entity.category, domain.category) },
            { assertEquals(entity.ageRating, domain.ageRating) },
            { assertEquals(entity.size, domain.size) },
            { assertEquals(entity.iconUrl, domain.iconUrl) },
            { assertEquals(entity.description, domain.description) },
            { assertEquals(entity.isInWishlist, domain.isInWishlist) }
        )
    }

    @Test
    fun `toDomain strictly sets screenshotUrlList to null ignoring db garbage`() {
        val entity = createEntity(screenshots = "some_legacy_string")
        val domain = mapper.toDomain(entity)

        assertNull(domain.screenshotUrlList)
    }

    @Test
    fun `toEntity then toDomain preserves all non screenshot fields`() {
        val original = createDomain(isInWishlist = true)
        val result = mapper.toDomain(mapper.toEntity(original))

        assertAll(
            { assertEquals(original.id, result.id) },
            { assertEquals(original.name, result.name) },
            { assertEquals(original.developer, result.developer) },
            { assertEquals(original.category, result.category) },
            { assertEquals(original.ageRating, result.ageRating) },
            { assertEquals(original.size, result.size) },
            { assertEquals(original.iconUrl, result.iconUrl) },
            { assertEquals(original.description, result.description) },
            { assertEquals(original.isInWishlist, result.isInWishlist) }
        )
    }
}