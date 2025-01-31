package com.na.rmuniverse.data

import com.na.rmuniverse.data.network.Client
import com.na.rmuniverse.entity.Character
import javax.inject.Inject

class Repository @Inject constructor(private val client: Client) {

    suspend fun getCharactersList(page: Int): List<Character> {
        return client.requests.getCharacters(page).results
    }

}