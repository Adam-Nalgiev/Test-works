package com.na.rmuniverse.data.model.dbo

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import com.na.rmuniverse.entity.Origin

@Immutable
data class OriginDbo(

    @ColumnInfo(name = "origin_name")
    override val name: String,

    @ColumnInfo(name = "origin_url")
    override val url: String

) : Origin