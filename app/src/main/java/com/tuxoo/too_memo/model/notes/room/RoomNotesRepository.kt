package com.tuxoo.too_memo.model.notes.room

import com.tuxoo.too_memo.model.notes.NotesRepository
import com.tuxoo.too_memo.model.notes.entity.Note
import com.tuxoo.too_memo.model.topics.entity.Topic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomNotesRepository(
    private val notesDao: NotesDao
) : NotesRepository {

    override suspend fun getByTopic(topic: Topic): Flow<List<Note>> =
        notesDao.findByTopicId(topic.id).map {
            println(it.size)
            it.map { entity ->
                entity.toNote()
            }
        }

}