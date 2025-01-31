package com.mountech.binner.presentation.screen.request

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mountech.binner.R
import com.mountech.binner.data.model.dto.BinInfoDto
import com.mountech.binner.presentation.navigation.Screen
import com.mountech.binner.presentation.theme.BlackDescriptionStyle
import com.mountech.binner.presentation.theme.DarkMango
import com.mountech.binner.presentation.theme.GrayTitleStyle
import com.mountech.binner.presentation.theme.LightGray
import com.mountech.binner.presentation.theme.Mango
import com.mountech.binner.presentation.theme.Transparent
import com.mountech.binner.presentation.theme.White
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Не буду использовать hoist (lift) состояния в функциях, просто потому что это маленькое приложение
 */
@Composable
fun Request(
    onNavigateTo: (Screen) -> Unit,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    viewModel: RequestViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val bin = remember { mutableStateOf("") }  //при серьезном подходе хочется создать делегат для ограничения возможного значения
    val visibility = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val data = viewModel.binInfo.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Brush.verticalGradient(listOf(Mango, DarkMango)))
    ) {

        BinsHistoryButton(onNavigateTo = onNavigateTo, modifier = modifier.align(Alignment.TopEnd))

        Column(Modifier.align(Alignment.Center)) {
            BinInputField(bin = bin, modifier.padding(bottom = 48.dp))

            BinInformation(data = data.value, visibility = visibility, modifier = modifier)

            SendButton(
                bin = bin,
                scope = coroutineScope,
                dataVisibility = visibility,
                viewModel = viewModel,
                modifier = modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
private fun BinInputField(
    bin: MutableState<String> = remember { mutableStateOf("") },
    modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = bin.value,
        onValueChange = {
            if (bin.value.length <= 8) {
                bin.value = it
            } else {
                bin.value = it.substring( //плохой подход, может приводить к излишним рекомпозициям
                    startIndex = 0,
                    endIndex = 7
                )
            }
        },
        label = { Text(text = stringResource(R.string.enter_bin)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        maxLines = 1,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = White,
            focusedBorderColor = Mango,
            unfocusedLabelColor = LightGray,
            focusedTextColor = White,
            unfocusedTextColor = White
        )
    )
}

@Composable
private fun SendButton(
    bin: MutableState<String>,
    scope: CoroutineScope,
    dataVisibility: MutableState<Boolean>,
    viewModel: RequestViewModel,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = {
            scope.launch(Dispatchers.IO) {
                viewModel.getBinInfo(bin.value)
                dataVisibility.value = true
            }
        },
        colors = ButtonDefaults.outlinedButtonColors(containerColor = Transparent),
        border = BorderStroke(1.dp, White),
        modifier = modifier.padding(top = 8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = stringResource(R.string.send),
            color = White,
            modifier = modifier.padding(8.dp)
        )
    }
}

@Composable
private fun BinsHistoryButton(
    onNavigateTo: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {

    Button(
        onClick = { onNavigateTo(Screen.History) },
        colors = ButtonDefaults.buttonColors(containerColor = Transparent),
        modifier = modifier
            .wrapContentSize()
            .padding(8.dp)
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_time),
            contentDescription = stringResource(R.string.descr_history_screen)
        )
    }
}

@Composable
private fun BinInformation(
    data: BinInfoDto,
    visibility: MutableState<Boolean> = remember { mutableStateOf(false) },
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(modifier = modifier, visible = visibility.value) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround,
            modifier = modifier
        ) {
            items(count = 8) { rowIndex ->
                when (rowIndex) {
                    0 -> InfoPair(
                        titleText = stringResource(R.string.title_scheme),
                        descrText = data.scheme!!
                    )

                    1 -> InfoPair(
                        titleText = stringResource(R.string.title_brand),
                        descrText = data.brand!!
                    )

                    2 -> InfoPair(
                        titleText = stringResource(R.string.title_length),
                        descrText = data.number?.length.toString()
                    )

                    3 -> InfoPair(
                        titleText = stringResource(R.string.title_luhn),
                        descrText = data.number?.luhn.toString()
                    )

                    4 -> InfoPair(
                        titleText = stringResource(R.string.title_type),
                        descrText = data.type!!
                    )

                    5 -> InfoPair(
                        titleText = stringResource(R.string.title_prepaid),
                        descrText = stringResource(R.string.no)
                    )

                    6 -> InfoPair(
                        titleText = stringResource(R.string.title_country),
                        descrText = data.country?.name!!
                    )

                    7 -> InfoPair(
                        titleText = stringResource(R.string.title_bank),
                        descrText = data.bank?.name!!
                    )

                    else -> InfoPair("-", "-")
                }
            }
        }
    }
}

@Composable
private fun InfoPair(
    titleText: String,
    descrText: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(top = 8.dp)
    ) {
        GrayTitleText(titleText)
        BlackDescriptionText(descrText)
    }
}

@Composable
private fun GrayTitleText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = GrayTitleStyle,
        modifier = modifier
    )
}

@Composable
private fun BlackDescriptionText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = BlackDescriptionStyle,
        modifier = modifier
    )
}
