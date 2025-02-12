package com.na.rmuniverse.presentation.screen.viewmodel

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.na.rmuniverse.domain.GetCharactersListUseCase
import com.na.rmuniverse.entity.Character
import com.na.rmuniverse.presentation.screen.pagination.CharactersPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase
) : ViewModel() {

    var characters: Flow<PagingData<Character>> =
        Pager(
            config = PagingConfig(20),
            initialKey = 1,
            pagingSourceFactory = { CharactersPagingSource(getCharactersListUseCase) }
        ).flow.cachedIn(viewModelScope)

    fun watchEpisodes(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW).setData(url.toUri())
        context.startActivity(intent)
    }
}