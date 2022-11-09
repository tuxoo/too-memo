package com.tuxoo.too_memo.model.notes.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.tuxoo.too_memo.model.notes.room.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes WHERE topic_id = :topicId")
    fun findByTopicId(topicId: Long): Flow<List<NoteEntity>>

    @Delete
    fun delete(note: NoteEntity)
}