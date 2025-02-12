package com.na.rmuniverse.data.model.dto

import androidx.compose.runtime.Immutable
import com.na.rmuniverse.entity.Origin

@Immutable
data class OriginDto(
    override val name: String,
    override val url: String
) : Origin