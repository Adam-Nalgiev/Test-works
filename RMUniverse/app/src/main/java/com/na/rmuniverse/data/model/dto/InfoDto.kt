package com.na.rmuniverse.data.model.dto

import com.na.rmuniverse.entity.Info

data class InfoDto(

    override val count: Int,
    override val pages: Int,
    override val next: String,
    override val prev: String?

) : Info
