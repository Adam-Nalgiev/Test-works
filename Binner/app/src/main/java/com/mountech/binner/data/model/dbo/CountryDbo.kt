package com.mountech.binner.data.model.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.mountech.binner.entity.Country

@Entity(tableName = "country")
data class CountryDbo(
    @ColumnInfo(name = "numeric")
    override val numeric: Int? = 0,

    @ColumnInfo(name = "alpha2")
    override val alpha2: String? = "-",

    @ColumnInfo(name = "country_name")
    override val name: String? = "-",

    @ColumnInfo(name = "emoji")
    override val emoji: String? = "-",

    @ColumnInfo(name = "currency")
    override val currency: String? = "-",

    @ColumnInfo(name = "latitude")
    override val latitude: Int? = 0,

    @ColumnInfo(name = "longitude")
    override val longitude: Int? = 0,

) : Country
