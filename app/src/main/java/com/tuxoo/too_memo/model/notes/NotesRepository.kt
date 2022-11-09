package com.tuxoo.too_memo.model.notes

import com.tuxoo.too_memo.model.notes.entity.Note
import com.tuxoo.too_memo.model.topics.entity.Topic
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun getByTopic(topic: Topic): Flow<List<Note>>

    suspend fun delete(note: Note)
}