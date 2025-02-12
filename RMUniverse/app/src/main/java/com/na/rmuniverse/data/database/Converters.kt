package com.na.rmuniverse.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters constructor() {
    private val type = object : TypeToken<List<String>>() {}.type

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toStringList(json: String): List<String> {
        return Gson().fromJson(json, type)
    }
}