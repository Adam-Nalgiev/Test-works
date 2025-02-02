package com.mountech.binner.presentation.screen.history.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mountech.binner.domain.GetBinsHistoryUseCase
import com.mountech.binner.entity.BinInfo
import com.mountech.binner.presentation.screen.history.pagination.HistoryPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getBinsHistoryUseCase: GetBinsHistoryUseCase,
) : ViewModel() {

    var bins: Flow<PagingData<BinInfo>> =
        Pager(
            config = PagingConfig(10),
            initialKey = null,
            pagingSourceFactory = { HistoryPagingSource(getBinsHistoryUseCase) }
        ).flow.cachedIn(viewModelScope)
}