package com.flexcode.freetogame.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LaptopWindows
import androidx.compose.material.icons.filled.WebStories
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flexcode.freetogame.domain.models.FreeGame
import com.flexcode.freetogame.utils.AsyncImage
import com.flexcode.freetogame.utils.ShimmerImagePlaceHolder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameItem(
    modifier: Modifier = Modifier,
    game: FreeGame, onClick: (FreeGame) -> Unit
) {

    Card(
        onClick = {
                  onClick(game)
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(0.5.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            AsyncImage(
                imageUrl = game.thumbnail,
                contentDescription = "Game",
                contentScale = ContentScale.Crop,
                loadingPlaceHolder = {
                    ShimmerImagePlaceHolder()
                },
                modifier = modifier.fillMaxWidth()
                    .height(220.dp),
                errorPlaceHolder = {},
                alignment = Alignment.Center,
                alpha = DefaultAlpha,
                coloFilter = null,
                filterQuality = DrawScope.DefaultFilterQuality,
            )

            Row(
                modifier.fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = game.title, fontSize = 17.sp, fontWeight = FontWeight.SemiBold)

                Row(
                    modifier = modifier.background(
                        MaterialTheme.colorScheme.secondary, RoundedCornerShape(4.dp)
                    )
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "FREE",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontWeight = FontWeight.SemiBold
                    )

                }
            }

            Text(
                text = game.shortDescription, fontSize = 15.sp,
                maxLines = 1, modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                fontWeight = FontWeight.W300
            )


            Row(
                modifier.fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp, start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    modifier = modifier.background(
                        MaterialTheme.colorScheme.secondary, RoundedCornerShape(4.dp)
                    )
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = game.genre,
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                val icon = if (game.platform.lowercase().contains("pc")) {
                    Icons.Default.LaptopWindows
                } else if (game.platform.lowercase().contains("browser")) {
                    Icons.Default.WebStories
                } else {
                    null
                }

                icon?.let {
                    Icon(
                        imageVector = it, contentDescription = "Filters",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = modifier.padding(start = 8.dp).size(18.dp)
                    )
                }


            }
        }
    }

}