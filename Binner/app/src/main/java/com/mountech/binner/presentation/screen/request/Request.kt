package com.mountech.binner.presentation.screen.request

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mountech.binner.R
import com.mountech.binner.presentation.navigation.Screen
import com.mountech.binner.presentation.theme.DarkMango
import com.mountech.binner.presentation.theme.LightGray
import com.mountech.binner.presentation.theme.Mango
import com.mountech.binner.presentation.theme.Transparent
import com.mountech.binner.presentation.theme.White
import com.mountech.binner.presentation.ui.BinInformation
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
        BinsHistoryButton(
            onNavigateTo = onNavigateTo,
            modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center
        ) {
            BinInputField(bin = bin, modifier = modifier.align(Alignment.CenterHorizontally))

            BinInformation(
                data = data.value,
                visibility = visibility,
                modifier = modifier.align(Alignment.CenterHorizontally)
            )

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
    bin: MutableState<String>,
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
        modifier = modifier.padding(bottom = 48.dp),
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = White,
            focusedBorderColor = Mango,
            unfocusedLabelColor = LightGray,
            focusedTextColor = White,
            unfocusedTextColor = White
        ),
        textStyle = TextStyle(textAlign = TextAlign.Center)
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
                if (bin.value.length == 8) viewModel.getBinInfo(bin.value)
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
        contentPadding = PaddingValues(0.dp),
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