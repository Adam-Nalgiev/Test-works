package com.na.rmuniverse.data.model.dto

import androidx.compose.runtime.Immutable
import com.na.rmuniverse.entity.Location

@Immutable
data class LocationDto(
    override val name: String,
    override val url: String
) : Location