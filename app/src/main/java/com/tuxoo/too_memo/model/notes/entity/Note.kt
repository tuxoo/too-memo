package com.tuxoo.too_memo.model.notes.entity

import com.tuxoo.too_memo.model.notes.room.entity.NoteEntity
import java.time.Instant

data class Note(
    val id: Long,
    val title: String,
    val description: String,
    val createdAt: Instant,
    val lastModified: Instant,
    val topicId: Long,
) {
    fun toEntity(): NoteEntity = NoteEntity(
        id = id,
        title = title,
        description = description,
        createdAt = createdAt.epochSecond,
        lastModified = lastModified.epochSecond,
        topicId = topicId,
    )
}
