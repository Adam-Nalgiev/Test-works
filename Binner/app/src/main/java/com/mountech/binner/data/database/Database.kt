package com.mountech.binner.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mountech.binner.data.model.dbo.BinInfoDbo

@Database(
    entities = [BinInfoDbo::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun dao(): Dao
}