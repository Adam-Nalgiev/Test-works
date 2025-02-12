package com.na.rmuniverse.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.na.rmuniverse.data.model.dbo.CharacterDbo

@Dao
interface Dao {

    @Query("SELECT * FROM character ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getAll(
        limit: Int,
        offset: Int
    ): List<CharacterDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(characters: CharacterDbo)

}