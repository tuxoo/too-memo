package com.tuxoo.too_memo.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tuxoo.too_memo.model.notes.room.NotesDao
import com.tuxoo.too_memo.model.notes.room.entity.NoteEntity
import com.tuxoo.too_memo.model.topics.room.TopicsDao
import com.tuxoo.too_memo.model.topics.room.entity.TopicEntity

@Database(
    version = 1,
    entities = [
        TopicEntity::class,
        NoteEntity::class,
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getTopicsDao(): TopicsDao

    abstract fun getNotesDao(): NotesDao
}