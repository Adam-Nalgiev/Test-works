package com.na.rmuniverse.data.network

import com.na.rmuniverse.data.model.dto.CharactersListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharactersListDto
}