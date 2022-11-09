package com.tuxoo.too_memo.dependency

import android.content.Context
import androidx.room.Room
import com.tuxoo.too_memo.model.notes.NotesRepository
import com.tuxoo.too_memo.model.notes.room.RoomNotesRepository
import com.tuxoo.too_memo.model.room.AppDatabase
import com.tuxoo.too_memo.model.topics.TopicsRepository
import com.tuxoo.too_memo.model.topics.room.RoomTopicsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        context: Context
    ): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .createFromAsset(ASSET_FILE)
            .build()

    @Provides
    @Singleton
    fun provideTopicsRepository(database: AppDatabase): TopicsRepository =
        RoomTopicsRepository(database.getTopicsDao())

    @Provides
    @Singleton
    fun provideNotesRepository(database: AppDatabase): NotesRepository =
        RoomNotesRepository(database.getNotesDao())

    companion object {
        private const val DB_NAME = "too_memo"
        private const val ASSET_FILE = "init_db.db"
    }
}