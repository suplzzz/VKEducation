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
import com.suplz.vkeducation.ui.theme.VKEducationTheme

@Composable
fun AppListContent(
    apps: List<AppListItem>,
    innerPadding: PaddingValues,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .padding(top = innerPadding.calculateTopPadding())
            .fillMaxSize(),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        color = MaterialTheme.colorScheme.onPrimary
    ) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding())
        ) {
            itemsIndexed(apps) { index, appListItem ->

                AppListItemCard(
                    appListItem,
                    onClick = onClick
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
            AppListItem(
                name = "VK Видео: кино, сериалы, шоу",
                category = "Развлечения",
                iconUrl = "",
                description = "Смотри шоу, мультики, ТВ, сериалы и блогеров"
            ),
            AppListItem(
                name = "СберБанк Онлайн",
                category = "Финансы",
                iconUrl = "",
                description = "Больше чем банк"
            ),
            AppListItem(
                name = "Авито – услуги, работа, авто",
                category = "Покупки",
                iconUrl = "",
                description = "Объявления: услуги, товары, недвижимость"
            )
        )
        AppListContent(
            apps = mockApps,
            innerPadding = PaddingValues(0.dp),
            onClick = {}
        )
    }
}