package com.tuxoo.too_memo.dependency

import android.content.Context
import androidx.room.Room
import com.tuxoo.too_memo.model.room.AppDatabase
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

    companion object {
        private const val DB_NAME = "too_memo"
        private const val ASSET_FILE = "init_db.db"
    }
}