package com.na.rmuniverse.di

import android.content.Context
import androidx.room.Room
import com.na.rmuniverse.data.database.Dao
import com.na.rmuniverse.data.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
            "R&M Universe Database"
        ).build()
    }

    @Provides
    fun provideDao(database: Database): Dao {
        return database.dao()
    }
}