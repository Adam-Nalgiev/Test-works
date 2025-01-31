package com.mountech.binner.data.model.dto

import com.mountech.binner.entity.NumberInfo

data class NumberInfoDto(

    override val length: Int? = 0,
    override val luhn: Boolean? = false,

) : NumberInfo
