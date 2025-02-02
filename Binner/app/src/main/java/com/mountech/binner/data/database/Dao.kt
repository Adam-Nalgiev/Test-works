package com.mountech.binner.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mountech.binner.data.model.dbo.BinInfoDbo

@Dao
interface Dao {

    @Query("SELECT * FROM bin_info ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getAll(
        limit: Int,
        offset: Int
    ): List<BinInfoDbo>

    @Query("DELETE  from bin_info")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(binInfoDbo: BinInfoDbo)
}