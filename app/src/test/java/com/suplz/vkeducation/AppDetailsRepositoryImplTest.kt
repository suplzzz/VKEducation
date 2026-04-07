package com.suplz.vkeducation

import com.suplz.vkeducation.data.AppApi
import com.suplz.vkeducation.data.appdetails.AppDetailsDto
import com.suplz.vkeducation.data.appdetails.AppDetailsMapper
import com.suplz.vkeducation.data.appdetails.AppDetailsRepositoryImpl
import com.suplz.vkeducation.data.appdetails.local.AppDetailsDao
import com.suplz.vkeducation.data.appdetails.local.AppDetailsEntity
import com.suplz.vkeducation.data.appdetails.local.AppDetailsEntityMapper
import com.suplz.vkeducation.domain.Category
import com.suplz.vkeducation.domain.appdetails.AppDetails
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class AppDetailsRepositoryImplTest {

    private val appApi: AppApi = mockk()
    private val dao: AppDetailsDao = mockk()
    private val mapper: AppDetailsMapper = mockk()
    private val entityMapper: AppDetailsEntityMapper = mockk()
    private lateinit var repository: AppDetailsRepositoryImpl

    @BeforeEach
    fun setUp() {
        repository = AppDetailsRepositoryImpl(appApi, dao, mapper, entityMapper)
    }

    private fun createDomain(
        id: String = "app_123",
        name: String = "Test App",
        developer: String = "Test Dev",
        category: Category = Category.APP,
        ageRating: Int = 12,
        size: Float = 45.5f,
        iconUrl: String = "https://example.com/icon.png",
        screenshotUrlList: List<String>? = listOf("https://example.com/a.png"),
        description: String = "Test description",
        isInWishlist: Boolean = false
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
        id: String = "app_123",
        name: String = "Test App",
        developer: String = "Test Dev",
        category: Category = Category.APP,
        ageRating: Int = 12,
        size: Float = 45.5f,
        iconUrl: String = "https://example.com/icon.png",
        screenshots: String? = "https://example.com/a.png",
        description: String = "Test description",
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

    private fun createDto(
        id: String = "app_123",
        name: String = "Test App",
        developer: String = "Test Dev",
        category: Category = Category.APP,
        ageRating: Int = 12,
        size: Double = 45.5,
        icon: String = "https://example.com/icon.png",
        screenshots: List<String>? = listOf("https://example.com/a.png"),
        description: String = "Test description"
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
    fun `get should return mapped entity from database and not call api`() = runTest {
        val requestedId = "local_app"
        val localEntity = createEntity(id = requestedId)
        val expectedDomain = createDomain(id = requestedId)

        every { dao.getAppDetails(requestedId) } returns flowOf(localEntity)
        every { entityMapper.toDomain(localEntity) } returns expectedDomain

        val result = repository.get(requestedId)

        assertAll(
            { assertEquals(requestedId, result.id) },
            { assertEquals(expectedDomain, result) }
        )
        verify(exactly = 1) { dao.getAppDetails(requestedId) }
        verify(exactly = 1) { entityMapper.toDomain(localEntity) }
        coVerify(exactly = 0) { appApi.getAppDetails(any()) }
    }

    @Test
    fun `get should fetch from api save to database and return domain when local is null`() = runTest {
        val requestedId = "remote_app"
        val fetchedDto = createDto(id = requestedId)
        val mappedDomain = createDomain(id = requestedId)
        val entityToSave = createEntity(id = requestedId)

        every { dao.getAppDetails(requestedId) } returns flowOf(null)
        coEvery { appApi.getAppDetails(requestedId) } returns fetchedDto
        every { mapper.toDomain(fetchedDto) } returns mappedDomain
        every { entityMapper.toEntity(mappedDomain) } returns entityToSave
        coEvery { dao.insertAppDetails(entityToSave) } returns Unit

        val result = repository.get(requestedId)

        assertAll(
            { assertEquals(requestedId, result.id) },
            { assertEquals(mappedDomain, result) }
        )
        verify(exactly = 1) { dao.getAppDetails(requestedId) }
        coVerify(exactly = 1) { appApi.getAppDetails(requestedId) }
        verify(exactly = 1) { mapper.toDomain(fetchedDto) }
        verify(exactly = 1) { entityMapper.toEntity(mappedDomain) }
        coVerify(exactly = 1) { dao.insertAppDetails(entityToSave) }
    }

    @Test
    fun `observeAppDetails should map non null flow from database`() = runTest {
        val streamId = "stream_app"
        val activeEntity = createEntity(id = streamId)
        val activeDomain = createDomain(id = streamId)

        every { dao.getAppDetails(streamId) } returns flowOf(activeEntity)
        every { entityMapper.toDomain(activeEntity) } returns activeDomain

        val result = repository.observeAppDetails(streamId).first()

        assertAll(
            { assertNotNull(result) },
            { assertEquals(streamId, result.id) },
            { assertEquals(activeDomain, result) }
        )
        verify(exactly = 1) { dao.getAppDetails(streamId) }
        verify(exactly = 1) { entityMapper.toDomain(activeEntity) }
    }

    @Test
    fun `toggleWishlist should inverse current wishlist status to true`() = runTest {
        val targetId = "wishlist_app"
        val currentEntity = createEntity(id = targetId, isInWishlist = false)

        every { dao.getAppDetails(targetId) } returns flowOf(currentEntity)
        coEvery { dao.updateWishlistStatus(targetId, true) } returns Unit

        repository.toggleWishlist(targetId)

        verify(exactly = 1) { dao.getAppDetails(targetId) }
        coVerify(exactly = 1) { dao.updateWishlistStatus(targetId, true) }
    }

    @Test
    fun `toggleWishlist should inverse current wishlist status to false`() = runTest {
        val targetId = "wishlist_app"
        val currentEntity = createEntity(id = targetId, isInWishlist = true)

        every { dao.getAppDetails(targetId) } returns flowOf(currentEntity)
        coEvery { dao.updateWishlistStatus(targetId, false) } returns Unit

        repository.toggleWishlist(targetId)

        verify(exactly = 1) { dao.getAppDetails(targetId) }
        coVerify(exactly = 1) { dao.updateWishlistStatus(targetId, false) }
    }

    @Test
    fun `toggleWishlist should not update status if entity is null`() = runTest {
        val targetId = "missing_app"
        every { dao.getAppDetails(targetId) } returns flowOf(null)

        repository.toggleWishlist(targetId)

        verify(exactly = 1) { dao.getAppDetails(targetId) }
        coVerify(exactly = 0) { dao.updateWishlistStatus(any(), any()) }
    }
}