package com.na.rmuniverse.data.model.dto

import com.na.rmuniverse.entity.Origin

data class OriginDto(
    override val name: String,
    override val url: String
) : Origin