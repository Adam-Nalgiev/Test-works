package com.mountech.binner.data.model.dto

import com.mountech.binner.entity.BinInfo

data class BinInfoDto(

    override val number: NumberInfoDto?,
    override val scheme: String? = "-",
    override val type: String? = "-",
    override val brand: String? = "-",
    override val country: CountryDto?,
    override val bank: BankDto?,

    ) : BinInfo
