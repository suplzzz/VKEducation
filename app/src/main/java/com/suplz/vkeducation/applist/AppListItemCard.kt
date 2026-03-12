package com.suplz.vkeducation.applist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.suplz.vkeducation.R
import com.suplz.vkeducation.mapper.toUiString
import com.suplz.vkeducation.model.Category
import com.suplz.vkeducation.ui.theme.VKEducationTheme

@Composable
fun AppListItemCard(
    appListItem: AppListItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = appListItem.iconUrl,
            error = painterResource(R.drawable.sber_logo),
            contentDescription = stringResource(
                R.string.icon_name,
                appListItem.name
            ),
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .size(64.dp)
        )

        Column(
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Text(
                text = appListItem.name,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = appListItem.description,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = appListItem.category,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun AppListItemCardPreview() {
    VKEducationTheme {
        val mockApp = AppListItem(
            name = "СберБанк Онлайн",
            description = "Больше чем банк",
            category = Category.APP.toUiString(),
            iconUrl = ""
        )

        AppListItemCard(
            appListItem = mockApp,
            onClick = {}
        )
    }
}