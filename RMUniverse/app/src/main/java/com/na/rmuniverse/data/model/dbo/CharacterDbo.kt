package com.na.rmuniverse.data.model.dbo

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.na.rmuniverse.data.database.Converters
import com.na.rmuniverse.entity.Character

@Immutable
@Entity(tableName = "character")
data class CharacterDbo(

    @PrimaryKey
    @ColumnInfo(name = "id")
    override val id: Int,

    @ColumnInfo(name = "name")
    override val name: String,

    @ColumnInfo(name = "status")
    override val status: String,

    @ColumnInfo(name = "species")
    override val species: String,

    @ColumnInfo(name = "type")
    override val type: String,

    @ColumnInfo(name = "gender")
    override val gender: String,

    @Embedded
    override val origin: OriginDbo,

    @Embedded
    override val location: LocationDbo,

    @ColumnInfo(name = "image")
    override val image: String,


    @TypeConverters(Converters::class)
    override val episode: List<String>,

    @ColumnInfo(name = "url")
    override val url: String,

    @ColumnInfo(name = "created")
    override val created: String

): Character