package com.tuxoo.too_memo.model.notes.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.tuxoo.too_memo.model.notes.entity.Note
import com.tuxoo.too_memo.model.topics.room.entity.TopicEntity
import java.time.Instant

@Entity(
    tableName = "notes",
    foreignKeys = [
        ForeignKey(
            entity = TopicEntity::class,
            parentColumns = ["id"],
            childColumns = ["topic_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ]
)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val description: String,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "last_modified") val lastModified: Long,
    @ColumnInfo(name = "topic_id") val topicId: Long,
) {

    fun toNote(): Note = Note(
        id = id,
        title = title,
        description = description,
        createdAt = Instant.ofEpochSecond(createdAt),
        lastModified = Instant.ofEpochSecond(lastModified),
        topicId = topicId,
    )
}
