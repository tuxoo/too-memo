package com.tuxoo.too_memo.model.topics.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tuxoo.too_memo.model.topics.entity.Topic
import java.time.Instant

@Entity(
    tableName = "topics"
)
data class TopicEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "is_pinned") val isPinned: Int,
) {

    fun toTopic(): Topic = Topic(
        id = id,
        name = name,
        createdAt = Instant.ofEpochSecond(createdAt),
        isPinned = toBool(isPinned)
    )

    companion object {

        fun toBool(isPinned: Int): Boolean = isPinned == 1

        fun fromBool(isPinned: Boolean): Int = if (isPinned) 1 else 0
    }
}
