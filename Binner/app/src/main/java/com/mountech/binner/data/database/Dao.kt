package com.mountech.binner.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mountech.binner.data.model.dbo.BinInfoDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Query("SELECT * from bin_info")
    fun getAll(): Flow<List<BinInfoDbo>>

    @Query("DELETE  from bin_info")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = BinInfoDbo::class)
    suspend fun insert(binInfoDbo: BinInfoDbo)
}