package com.na.rmuniverse.presentation.screen.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.na.rmuniverse.domain.GetCharactersListUseCase
import com.na.rmuniverse.entity.Character

class CharactersPagingSource(
    private val getCharactersListUseCase: GetCharactersListUseCase
): PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: 1

        return runCatching {
            getCharactersListUseCase.execute(page)
        }.fold(
            onFailure = {
                Log.d("PAGING SOURCE", "FAIL $it")
                LoadResult.Error(it)
            },
            onSuccess = {
                    Log.d("PAGING SOURCE", "SUCCESS $it")
                    LoadResult.Page(
                        data = it,
                        nextKey = page + 1,
                        prevKey = if (page > 1) page - 1 else null
                    )
            }
        )
    }
}