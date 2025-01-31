package com.mountech.binner.data.model.dbo

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mountech.binner.entity.BinInfo

@Entity(tableName = "bin_info")
data class BinInfoDbo(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int?,

    @Embedded
    override val number: NumberInfoDbo?,

    @ColumnInfo(name = "scheme")
    override val scheme: String?,

    @ColumnInfo(name = "type")
    override val type: String?,

    @ColumnInfo(name = "brand")
    override val brand: String?,

    @Embedded
    override val country: CountryDbo?,

    @Embedded
    override val bank: BankDbo?

) : BinInfo
