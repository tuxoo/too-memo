package com.tuxoo.too_memo.model.topics.entity

import com.tuxoo.too_memo.model.topics.room.entity.TopicEntity
import java.time.Instant

data class Topic(
    val id: Long,
    val name: String,
    val createdAt: Instant,
    val isPinned: Boolean,
) {

    fun toTopicEntity(): TopicEntity = TopicEntity(
        id = id,
        name = name,
        createdAt = createdAt.epochSecond,
        isPinned = TopicEntity.fromBool(isPinned)
    )
}
