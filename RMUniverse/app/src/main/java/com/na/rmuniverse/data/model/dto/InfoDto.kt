package com.na.rmuniverse.data.model.dto

import androidx.compose.runtime.Immutable
import com.na.rmuniverse.entity.Info

@Immutable
data class InfoDto(

    override val count: Int,
    override val pages: Int,
    override val next: String,
    override val prev: String?

) : Info
