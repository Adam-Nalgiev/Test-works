package com.mountech.binner.presentation.screen.history.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mountech.binner.domain.GetBinsHistoryUseCase
import com.mountech.binner.entity.BinInfo

class HistoryPagingSource(
    private val getBinsHistoryUseCase: GetBinsHistoryUseCase
): PagingSource<Int, BinInfo>() {
    override fun getRefreshKey(state: PagingState<Int, BinInfo>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BinInfo> {
        val page = params.key ?: 1

        return runCatching {
            getBinsHistoryUseCase.execute(params.loadSize, 0)
        }.fold(
            onFailure = {
                Log.d("PAGING SOURCE", "FAIL $it")
                LoadResult.Error(it)
            },
            onSuccess = {
                Log.d("PAGING SOURCE", "SUCCESS $it")
                LoadResult.Page(
                    data = it,
                    nextKey = null,
                    prevKey = if (page > 1) page - 1 else null
                )
            }
        )
    }
}