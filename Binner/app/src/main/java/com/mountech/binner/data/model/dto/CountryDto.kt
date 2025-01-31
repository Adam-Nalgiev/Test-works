package com.mountech.binner.data.model.dto

import com.mountech.binner.entity.Country

data class CountryDto(

    override val numeric: Int? = 0,
    override val alpha2: String? = "-",
    override val name: String? = "-",
    override val emoji: String? = "-",
    override val currency: String? = "-",
    override val latitude: Int? = 0,
    override val longitude: Int? = 0,

) : Country
