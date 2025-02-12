package com.na.rmuniverse.data.model.dbo

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import com.na.rmuniverse.entity.Location

@Immutable
data class LocationDbo(

    @ColumnInfo(name = "location_name")
    override val name: String,

    @ColumnInfo(name = "location_id")
    override val url: String
) : Location