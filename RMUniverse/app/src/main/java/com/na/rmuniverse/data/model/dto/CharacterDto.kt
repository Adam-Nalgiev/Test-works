package com.na.rmuniverse.data.model.dto

import androidx.compose.runtime.Immutable
import com.na.rmuniverse.entity.Character

@Immutable
data class CharacterDto(

    override val id: Int,
    override val name: String,
    override val status: String,
    override val species: String,
    override val type: String,
    override val gender: String,
    override val origin: OriginDto,
    override val location: LocationDto,
    override val image: String,
    override val episode: List<String>,
    override val url: String,
    override val created: String

): Character
