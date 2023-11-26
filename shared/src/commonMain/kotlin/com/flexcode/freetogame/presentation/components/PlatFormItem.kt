package com.flexcode.freetogame.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlatformItem(
    modifier: Modifier = Modifier,
    category: String = "All",
    selectedCategory: String = "All",
    onClick:(String)->Unit
) {
    Row(
        modifier = modifier.background(
            color = if (selectedCategory == category)
                MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(8.dp)
        )
            .border(1.dp,MaterialTheme.colorScheme.tertiaryContainer,RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick(category) }
    ) {
        Text(
            text = category, color = MaterialTheme.colorScheme.onTertiaryContainer,
            fontWeight = if (selectedCategory == category) FontWeight.SemiBold else FontWeight.Normal,
            fontSize = 15.sp
        )
    }
}

