package com.na.rmuniverse.data.model.dto

import com.na.rmuniverse.entity.Location

data class LocationDto(
    override val name: String,
    override val url: String
) : Location