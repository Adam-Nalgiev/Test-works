package com.na.rmuniverse.data.model.dto

import com.na.rmuniverse.entity.CharactersList

data class CharactersListDto(

    override val info: InfoDto,
    override val results: List<CharacterDto>

) : CharactersList
