package com.na.rmuniverse.data.model.dto

import androidx.compose.runtime.Immutable
import com.na.rmuniverse.entity.CharactersList

@Immutable
data class CharactersListDto(

    override val info: InfoDto,
    override val results: List<CharacterDto>

) : CharactersList
