package com.na.rmuniverse.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.na.rmuniverse.data.model.dbo.CharacterDbo

@Database(
    entities = [CharacterDbo::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun dao(): Dao
}
