package com.mountech.binner.data.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Возможно, не правильно, что модуль здесь. Обычно для DI отдельная директория, но проект слишком мал для такого усложнения

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "Binner Database"
        ).build()
    }

    @Provides
    fun provideDao(database: Database): Dao {
        return database.dao()
    }
}