package com.suplz.vkeducation.presentation.applist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suplz.vkeducation.R
import com.suplz.vkeducation.presentation.ui.theme.RuStoreWhite
import com.suplz.vkeducation.presentation.ui.theme.VKEducationTheme

@Composable
fun AppListTopBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.rustore_color_logo),
            contentDescription = stringResource(R.string.rustore_logo),
            tint = RuStoreWhite
        )

        IconButton(
            onClick = {},
            enabled = true,
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = RuStoreWhite.copy(alpha = 0.25f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.square_4_outline_28),
                    contentDescription = stringResource(R.string.apps),
                    tint = RuStoreWhite
                )
            }

        }
    }
}
@Preview
@Composable
private fun Preview() {
    VKEducationTheme {
        AppListTopBar()
    }
}