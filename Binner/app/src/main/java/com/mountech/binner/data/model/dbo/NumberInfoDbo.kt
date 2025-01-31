package com.mountech.binner.data.model.dbo

import androidx.room.ColumnInfo
import com.mountech.binner.entity.NumberInfo

data class NumberInfoDbo(

    @ColumnInfo(name = "length")
    override val length: Int?,

    @ColumnInfo(name = "luhn")
    override val luhn: Boolean?,

    ) : NumberInfo