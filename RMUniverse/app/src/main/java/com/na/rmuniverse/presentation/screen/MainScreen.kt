package com.na.rmuniverse.presentation.screen

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.na.rmuniverse.R
import com.na.rmuniverse.entity.Character
import com.na.rmuniverse.presentation.screen.viewmodel.MainViewModel
import com.na.rmuniverse.presentation.theme.Black
import com.na.rmuniverse.presentation.theme.DarkGray
import com.na.rmuniverse.presentation.theme.Gray
import com.na.rmuniverse.presentation.theme.Green
import com.na.rmuniverse.presentation.theme.LightGray
import com.na.rmuniverse.presentation.theme.LightGreen
import com.na.rmuniverse.presentation.theme.LightOrange
import com.na.rmuniverse.presentation.theme.LightRed
import com.na.rmuniverse.presentation.theme.Orange
import com.na.rmuniverse.presentation.theme.Red

@Composable
fun CharactersListScreen(
    viewModel: MainViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    val charactersList = viewModel.characters.collectAsLazyPagingItems()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.White)
    ) {
        Column(Modifier.align(Alignment.Center)) {

            BasicText(
                text = stringResource(R.string.characters),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 8.dp, top = 40.dp, bottom = 16.dp)
            )

            AnimatedVisibility(
                charactersList.loadState.refresh is LoadState.Loading,
                Modifier.align(Alignment.CenterHorizontally)
            ) {
                LoadingProgressBar()
            }

            CharactersList(charactersList, viewModel)
        }
    }
}

@Composable
private fun CharactersList(
    charactersList: LazyPagingItems<Character>,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    Box {
        LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = modifier.fillMaxSize(),
        ) {
            items(charactersList.itemCount) { itemId ->
                val character = charactersList[itemId]
                with(character!!) {
                    CharacterItem(
                        imageUrl = image,
                        name = name,
                        origin = "$species, $gender",
                        location = location.name,
                        episodes = episode,
                        status = status,
                        viewModel = viewModel
                    )
                }
            }
            when (charactersList.loadState.append) {
                is LoadState.Error -> {
                    item {
                        Box(Modifier.fillMaxWidth()) {
                            ErrorItem(
                                charactersList = charactersList,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }

                is LoadState.Loading -> {
                    item {
                        Box(
                            Modifier.fillMaxWidth()
                        ) {
                            LoadingProgressBar(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }

                is LoadState.NotLoading -> Unit
            }

            if (charactersList.loadState.refresh is LoadState.Error) {
                item {
                    ErrorItem(
                        charactersList = charactersList,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
private fun CharacterItem(
    imageUrl: String,
    name: String,
    origin: String,
    location: String,
    episodes: List<String>,
    status: String,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .height(120.dp)
            .padding(start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            CharacterImage(imageUrl)

            CharacterInfo(name, origin, location, episodes, status, viewModel)
        }
    }
}

@NonRestartableComposable
@Composable
private fun CharacterImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = stringResource(R.string.descr_character_image),
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .clip(RoundedCornerShape(40.dp))
            .size(120.dp)
            .shimmerLoading()
    )
}

@NonRestartableComposable
@Composable
private fun CharacterInfo(
    name: String,
    origin: String,
    location: String,
    episodes: List<String>,
    status: String,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(start = 8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier.fillMaxWidth()) {
            BasicText(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            CharacterAliveStatus(status)
        }

        BasicText(text = origin)

        EpisodesButton(
            episodesUrl = episodes[0],
            viewModel = viewModel
        )

        LocationText(text = location)
    }
}

@NonRestartableComposable
@Composable
private fun CharacterAliveStatus(
    status: String,
    modifier: Modifier = Modifier
) {
    when (status) {
        "Alive" -> Row(modifier = modifier.background(LightGreen, RoundedCornerShape(16.dp))) {
            BasicText(
                stringResource(R.string.status_alive),
                textColor = Green,
                style = MaterialTheme.typography.bodyLarge,
                modifier = modifier.padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
            )
        }

        "Dead" -> Row(modifier = modifier.background(LightRed, RoundedCornerShape(16.dp))) {
            BasicText(
                stringResource(R.string.status_dead),
                textColor = Red,
                style = MaterialTheme.typography.bodyLarge,
                modifier = modifier.padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
            )
        }

        else -> Row(modifier = modifier.background(LightGray, RoundedCornerShape(16.dp))) {
            BasicText(
                stringResource(R.string.status_unknown),
                textColor = Gray,
                style = MaterialTheme.typography.bodyLarge,
                modifier = modifier.padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
            )
        }
    }
}

@NonRestartableComposable
@Composable
private fun EpisodesButton(
    context: Context = LocalContext.current,
    episodesUrl: String,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { viewModel.watchEpisodes(context, episodesUrl) },
        colors = ButtonDefaults.buttonColors(containerColor = LightOrange, contentColor = Orange),
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .height(32.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_play),
            contentDescription = stringResource(R.string.descr_ic_play)
        )
        BasicText(
            text = stringResource(R.string.watch_episodes),
            textColor = Orange,
            modifier = modifier.padding(start = 4.dp)
        )
    }

}

@NonRestartableComposable
@Composable
private fun LocationText(
    text: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_location),
            contentDescription = stringResource(R.string.descr_ic_location),
            modifier = modifier.align(Alignment.CenterVertically)
        )

        BasicText(
            text = text,
            textColor = DarkGray,
            modifier = modifier
                .padding(start = 2.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun BasicText(
    text: String,
    textColor: Color = Black,
    maxLines: Int = 1,
    align: TextAlign = TextAlign.Start,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    overflow: TextOverflow = TextOverflow.Visible,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = textColor,
        maxLines = maxLines,
        textAlign = align,
        style = style,
        overflow = overflow,
        modifier = modifier
    )
}

@Composable
private fun ErrorItem(charactersList: LazyPagingItems<Character>, modifier: Modifier = Modifier) {
    Column(modifier) {
        ErrorText()
        RetryButton(charactersList, Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
private fun ErrorText(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        BasicText(
            text = stringResource(R.string.error),
            textColor = Red,
            maxLines = 2,
            align = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun RetryButton(listToRetry: LazyPagingItems<Character>, modifier: Modifier = Modifier) {
    Button(
        onClick = { listToRetry.refresh() },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        modifier = modifier
    ) {
        Image(
            ImageVector.vectorResource(R.drawable.ic_restart),
            contentDescription = stringResource(R.string.descr_retry_button)
        )
    }
}

@Composable
private fun LoadingProgressBar(modifier: Modifier = Modifier) {
    CircularProgressIndicator(color = Orange, modifier = modifier)
}

//Не придумал куда его еще деть
//Можно из него сделать плейсхолдер c шиммерингом вместо анимации загрузки для всех элементов, но я сделал только для изображений
@Composable
fun Modifier.shimmerLoading(
    durationMillis: Int = 1000,
): Modifier {
    val label = ""
    val transition = rememberInfiniteTransition(label = label)

    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 500f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Restart,
        ),
        label = label,
    )

    return drawBehind {
        drawRect(
            brush = Brush.linearGradient(
                colors = listOf(
                    LightGray.copy(alpha = 0.2f),
                    LightGray.copy(alpha = 1.0f),
                    LightGray.copy(alpha = 0.2f),
                ),
                start = Offset(x = translateAnimation, y = translateAnimation),
                end = Offset(x = translateAnimation + 100f, y = translateAnimation + 100f),
            )
        )
    }
}