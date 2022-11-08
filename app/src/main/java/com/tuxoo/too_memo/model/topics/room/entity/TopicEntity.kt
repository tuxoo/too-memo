package com.tuxoo.too_memo.model.topics.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tuxoo.too_memo.model.topics.entity.Topic
import java.time.Instant
import java.time.format.DateTimeFormatter

@Entity(
    tableName = "topics"
)
data class TopicEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "is_pinned") val isPinned: Boolean,
) {
    fun toTopic(): Topic = Topic(
        id = id,
        name = name,
        createdAt = dateFormatter.format(Instant.ofEpochMilli(createdAt)),
        isPinned = isPinned
    )

    companion object {
        private const val formatPattern = "dd MMM yyyy"
        private val dateFormatter = DateTimeFormatter.ofPattern(formatPattern)
    }
}
