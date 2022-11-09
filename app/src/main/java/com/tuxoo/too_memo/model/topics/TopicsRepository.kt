package com.tuxoo.too_memo.model.topics

import com.tuxoo.too_memo.model.topics.entity.Topic
import kotlinx.coroutines.flow.Flow

interface TopicsRepository {

    suspend fun getAll(): Flow<List<Topic>>

    suspend fun add(topic: Topic)

    suspend fun delete(topic: Topic)

    suspend fun update(topic: Topic)
}