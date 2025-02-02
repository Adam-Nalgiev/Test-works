package com.mountech.binner.presentation.screen.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mountech.binner.R
import com.mountech.binner.entity.BinInfo
import com.mountech.binner.presentation.navigation.Screen
import com.mountech.binner.presentation.screen.history.viewmodel.HistoryViewModel
import com.mountech.binner.presentation.theme.DarkMango
import com.mountech.binner.presentation.theme.LightGray
import com.mountech.binner.presentation.theme.Mango
import com.mountech.binner.presentation.theme.Transparent
import com.mountech.binner.presentation.ui.BinInformation

@Composable
fun History(
    onNavigateTo: (Screen) -> Unit,
    paddingValues: PaddingValues,
    viewModel: HistoryViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val history = viewModel.bins.collectAsLazyPagingItems()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Brush.verticalGradient(listOf(Mango, DarkMango)))
    ) {
        Column {
            BackButton(onNavigateTo)

            BinsList(history)
        }
    }
}

@Composable
private fun BackButton(
    onNavigateTo: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onNavigateTo(Screen.Request) },
        modifier = modifier
            .wrapContentSize()
            .padding(top = 8.dp, start = 8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Transparent),
        contentPadding = PaddingValues(0.dp)
    ) {
        Image(
            ImageVector.vectorResource(R.drawable.ic_arrow),
            contentDescription = stringResource(R.string.descr_back_button)
        )
    }
}

@Composable
private fun BinsList(
    binInfo: LazyPagingItems<BinInfo>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        items(binInfo.itemCount) { id ->
            BinItem(binInfo[id])
        }
    }
}

@Composable
private fun BinItem(
    binInfo: BinInfo?,
    modifier: Modifier = Modifier
) {
    val visibility = remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
            .background(LightGray, RoundedCornerShape(16.dp))
    ) {
        if (binInfo != null)
            BinInformation(binInfo, visibility, modifier.wrapContentSize())
    }
}