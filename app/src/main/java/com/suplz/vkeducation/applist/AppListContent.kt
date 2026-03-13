package com.suplz.vkeducation.applist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suplz.vkeducation.model.AppSummary
import com.suplz.vkeducation.model.Category
import com.suplz.vkeducation.ui.theme.VKEducationTheme

@Composable
fun AppListContent(
    content: AppListState.Content,
    innerPadding: PaddingValues,
    onClick: () -> Unit,
    onLogoClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val apps = content.appList

    Surface(
        modifier = modifier
            .padding(top = innerPadding.calculateTopPadding())
            .fillMaxSize(),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding())
        ) {
            itemsIndexed(
                apps
            ) { index, appListItem ->

                AppListItemCard(
                    appListItem,
                    onClick = onClick,
                    onLogoClick = {
                        onLogoClick(appListItem.name)
                    }
                )

                if (index < apps.lastIndex) {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.25f),
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun AppListContentPreview() {
    VKEducationTheme {
        val mockApps = listOf(
            AppSummary(
                name = "VK Видео: кино, сериалы, шоу",
                category = Category.APP,
                iconUrl = "",
                description = "Смотри шоу, мультики, ТВ, сериалы и блогеров"
            ),
            AppSummary(
                name = "СберБанк Онлайн",
                category = Category.APP,
                iconUrl = "",
                description = "Больше чем банк"
            ),
            AppSummary(
                name = "Авито – услуги, работа, авто",
                category = Category.APP,
                iconUrl = "",
                description = "Объявления: услуги, товары, недвижимость"
            )
        )
        AppListContent(
            content = AppListState.Content(
                appList = mockApps
            ),
            innerPadding = PaddingValues(0.dp),
            onClick = {},
            onLogoClick = {}
        )
    }
}