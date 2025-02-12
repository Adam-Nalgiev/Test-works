package com.na.rmuniverse.data

import android.content.Context
import android.widget.Toast
import com.na.rmuniverse.R
import com.na.rmuniverse.data.database.Database
import com.na.rmuniverse.data.model.dbo.CharacterDbo
import com.na.rmuniverse.data.model.dbo.LocationDbo
import com.na.rmuniverse.data.model.dbo.OriginDbo
import com.na.rmuniverse.data.model.dto.CharacterDto
import com.na.rmuniverse.data.network.Client
import com.na.rmuniverse.entity.Character
import dagger.hilt.android.qualifiers.ApplicationContext
import okio.IOException
import javax.inject.Inject

class Repository @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val client: Client,
    private val database: Database
) {

    suspend fun getCharactersList(page: Int): List<Character> {
        try {
            val result = client.requests.getCharacters(page).results
            saveCharacters(result)
            return result
        } catch (e: IOException) {
            if (page == 1) notifyNoConnection()
            return getCachedList(page)
        }
    }

    private suspend fun getCachedList(page: Int, pageSize: Int = 20): List<Character> {
        val offsetCount = if (page == 1) 0 else pageSize
        return database.dao().getAll(pageSize, page * offsetCount)
    }

    private suspend fun saveCharacters(characters: List<CharacterDto>) {
        characters.forEach {
            database.dao().save(dtoToDboMapper(it))
        }
    }

    private fun dtoToDboMapper(dto: CharacterDto): CharacterDbo {
        return with(dto) {
            val newOrigin = OriginDbo(origin.name, origin.url)
            val newLocation = LocationDbo(name, url)
            CharacterDbo(
                id,
                name,
                status,
                species,
                type,
                gender,
                newOrigin,
                newLocation,
                image,
                episode,
                url,
                created
            )
        }
    }

    private fun notifyNoConnection() {
        Toast.makeText(context, R.string.cached_data_notification, Toast.LENGTH_SHORT).show()
    }
}