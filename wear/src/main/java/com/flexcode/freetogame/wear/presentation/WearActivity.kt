/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.flexcode.freetogame.wear.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import com.flexcode.freetogame.domain.models.FreeGame
import com.flexcode.freetogame.presentation.state.GamesState
import com.flexcode.freetogame.presentation.viewmodels.FreeToGameViewModel
import com.flexcode.freetogame.presentation.views.TopBarItem
import com.flexcode.freetogame.ui.FreeToGameTheme
import com.flexcode.freetogame.utils.AsyncImage
import org.koin.android.ext.android.inject

class WearActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: FreeToGameViewModel by inject()
            val state = viewModel.state.collectAsState()


            FreeToGameTheme(
                darkTheme = true, dynamicColor = true
            ) {
                WearApp(viewModel, state.value)
                //App(darkTheme = isSystemInDarkTheme(), dynamicColor = false)

            }
        }
    }
}

@Composable
fun WearApp(viewModel: FreeToGameViewModel, state: GamesState) {
    val showOptionsMenu = remember { mutableStateOf(false) }

    ScalingLazyColumn(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) {
        item {
            TopBarItem(
                onFilterClick = viewModel::setCategory,
                filters = viewModel.categories,
                showOptionsMenu = showOptionsMenu,
            )

        }


        items(state.allGames.size) { i ->
            GameWearItem(game = state.allGames[i], onClick = {
                //navigate to details todo
            })
        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameWearItem(
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
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        Row(
            modifier = modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                imageUrl = game.thumbnail,
                contentDescription = "Game",
                contentScale = ContentScale.Crop,
                loadingPlaceHolder = {
                },
                modifier = modifier
                    .width(50.dp)
                    .height(50.dp).clip(RoundedCornerShape(4.dp)),
                errorPlaceHolder = {},
                alignment = Alignment.Center,
                alpha = DefaultAlpha,
                coloFilter = null,
                filterQuality = DrawScope.DefaultFilterQuality,
            )

            Column(
                modifier
                    .fillMaxWidth().padding(horizontal = 8.dp),
            ) {
                Text(text = game.title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)

                Text(
                    text = game.shortDescription, fontSize = 13.sp,
                    maxLines = 1, modifier = modifier.padding( vertical = 8.dp),
                    fontWeight = FontWeight.W300
                )
            }


        }
    }

}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {

    //WearApp("Preview Android")
}

@Preview(device = Devices.WEAR_OS_LARGE_ROUND, showSystemUi = true)
@Composable
fun LargeDefaultPreview() {

    //WearApp("Preview Android")
}

@Composable
fun GamesWearView(

) {

}