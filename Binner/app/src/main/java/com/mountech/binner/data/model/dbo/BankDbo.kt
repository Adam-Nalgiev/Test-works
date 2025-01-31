package com.mountech.binner.data.model.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.mountech.binner.entity.Bank

data class BankDbo(

    @ColumnInfo(name = "bank_name")
    override val name: String?,

    ) : Bank
