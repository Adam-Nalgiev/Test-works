package com.mountech.binner.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mountech.binner.R
import com.mountech.binner.entity.BinInfo
import com.mountech.binner.presentation.theme.BlackDescriptionStyle
import com.mountech.binner.presentation.theme.GrayTitleStyle

@Composable
fun BinInformation(
    data: BinInfo,
    visibility: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(modifier = modifier, visible = visibility.value) {
        InfoTable(data = data, modifier = modifier)
    }
}

@Composable
private fun InfoTable(
    data: BinInfo,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(2) { id ->
            Column {
                when (id) {
                    0 -> {
                        InfoPair(
                            titleText = stringResource(R.string.title_scheme),
                            descrText = data.scheme!!
                        )
                        InfoPair(
                            titleText = stringResource(R.string.title_brand),
                            descrText = data.brand!!
                        )
                        InfoPair(
                            titleText = stringResource(R.string.title_length),
                            descrText = data.number?.length.toString()
                        )
                        InfoPair(
                            titleText = stringResource(R.string.title_luhn),
                            descrText = data.number?.luhn.toString()
                        )
                    }

                    1 -> {
                        InfoPair(
                            titleText = stringResource(R.string.title_type),
                            descrText = data.type!!
                        )
                        InfoPair(
                            titleText = stringResource(R.string.title_prepaid),
                            descrText = stringResource(R.string.no)
                        )
                        InfoPair(
                            titleText = stringResource(R.string.title_country),
                            descrText = data.country?.name!!
                        )
                        InfoPair(
                            titleText = stringResource(R.string.title_bank),
                            descrText = data.bank?.name!!
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InfoPair(
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