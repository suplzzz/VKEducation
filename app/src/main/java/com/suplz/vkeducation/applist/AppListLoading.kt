package com.suplz.vkeducation.applist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suplz.vkeducation.ui.theme.VKEducationTheme

@Composable
fun AppListLoading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,

    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun Preview() {
    VKEducationTheme {
        AppListLoading(modifier = Modifier.fillMaxSize())
    }
}