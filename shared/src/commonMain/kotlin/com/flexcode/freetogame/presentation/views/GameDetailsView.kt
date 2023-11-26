package com.flexcode.freetogame.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.flexcode.freetogame.domain.models.FreeGame
import com.flexcode.freetogame.presentation.viewmodels.FreeToGameViewModel
import com.flexcode.freetogame.utils.AsyncImage
import com.flexcode.freetogame.utils.ShimmerImagePlaceHolder
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class GameDetailsView(
    private val game: FreeGame
) : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val viewModel: FreeToGameViewModel by inject()
        val state = viewModel.state.collectAsState()

        LaunchedEffect(true) {
            viewModel.getGameDetails(game.id)
        }

        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)) {

            Column(Modifier.verticalScroll(rememberScrollState())) {


                TopItemComposable(game = game, onClick = {
                    navigator.pop()
                })

                AsyncImage(
                    imageUrl = game.thumbnail,
                    contentDescription = "Anime",
                    contentScale = ContentScale.Crop,
                    loadingPlaceHolder = {
                        ShimmerImagePlaceHolder()
                    },
                    modifier = Modifier.fillMaxWidth()
                        .height(250.dp),
                    errorPlaceHolder = {},
                    alignment = Alignment.Center,
                    alpha = DefaultAlpha,
                    coloFilter = null,
                    filterQuality = DrawScope.DefaultFilterQuality,
                )

                PlayNowComponent(onClick = {
                    //to webview
                }, game = game)


                //AnimatedVisibility(!state.value.isLoading) {
                Column(
                    Modifier.padding(horizontal = 16.dp)
                ) {

                    Text(
                        text = "About ${game.title}",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = state.value.gameDetails?.description ?: "",
                        fontSize = 15.sp,
                        modifier = Modifier.padding(vertical = 16.dp),
                        fontWeight = FontWeight.W300,
                        color = MaterialTheme.colorScheme.onSurface
                    )

//                    Text(
//                        text = "Additional information", fontSize = 16.sp,
//                        fontWeight = FontWeight.SemiBold,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )


                    Spacer(Modifier.height(24.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "${game.title} Screenshots", fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                    val screenshots = state.value.gameDetails?.screenshots ?: emptyList()
                    val height = (76 * screenshots.size).dp
                    Spacer(Modifier.height(16.dp))

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.height(height),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(screenshots.size) { i ->
                            AsyncImage(
                                imageUrl = screenshots[i].image,
                                contentDescription = "Anime",
                                contentScale = ContentScale.Crop,
                                loadingPlaceHolder = {
                                    ShimmerImagePlaceHolder(height = 110.dp)
                                },
                                modifier = Modifier.fillMaxWidth()
                                    .height(110.dp),
                                errorPlaceHolder = {},
                                alignment = Alignment.Center,
                                alpha = DefaultAlpha,
                                coloFilter = null,
                                filterQuality = DrawScope.DefaultFilterQuality,
                            )
                        }
                    }

                    Spacer(Modifier.height(24.dp))

                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Minimum system description", fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Text(
                            text = game.platform, fontSize = 16.sp,
                            fontWeight = FontWeight.W200,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }


                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = "OS", fontSize = 13.sp,
                        fontWeight = FontWeight.W200,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = state.value.gameDetails?.minimumSystemRequirements?.os ?: "",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W300,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = "Memory", fontSize = 13.sp,
                        fontWeight = FontWeight.W200,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = state.value.gameDetails?.minimumSystemRequirements?.memory ?: "",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W300,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = "Processor", fontSize = 13.sp,
                        fontWeight = FontWeight.W200,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = state.value.gameDetails?.minimumSystemRequirements?.processor
                            ?: "",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W300,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = "Graphics", fontSize = 13.sp,
                        fontWeight = FontWeight.W200,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = state.value.gameDetails?.minimumSystemRequirements?.graphics
                            ?: "",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W300,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                //}

            }
        }


    }

}

@Composable
fun PlayNowComponent(
    modifier: Modifier = Modifier, onClick: (String) -> Unit, game: FreeGame,
) {
    Row(
        modifier.fillMaxWidth().padding(
            vertical = 16.dp, horizontal = 24.dp
        ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = modifier.background(
                MaterialTheme.colorScheme.surface, RoundedCornerShape(4.dp)
            )
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "FREE",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )

        }

        Button(
            onClick = {
                onClick(game.gameUrl)
            },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiaryContainer),
            elevation = ButtonDefaults.buttonElevation(1.dp),
            shape = RoundedCornerShape(8.dp), contentPadding = PaddingValues(16.dp)
        ) {
            Text(
                text = "PLAY NOW", fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Spacer(modifier = modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = null,
                modifier = modifier.size(ButtonDefaults.IconSize),
                tint = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}

@Composable
private fun TopItemComposable(
    modifier: Modifier = Modifier, game: FreeGame, onClick: () -> Unit
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Default.ChevronLeft, contentDescription = "back navigate",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

        Text(
            text = game.title, fontSize = 18.sp, fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )


        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.ChevronLeft, contentDescription = null,
                tint = MaterialTheme.colorScheme.surface
            )
        }
    }
}