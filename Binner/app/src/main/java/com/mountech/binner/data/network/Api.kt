package com.mountech.binner.data.network

import com.mountech.binner.data.model.dto.BinInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("{code}")
    suspend fun requireInfo(
        @Path("code") code: String
    ): BinInfoDto?

}