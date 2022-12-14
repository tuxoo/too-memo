package com.tuxoo.too_memo.model.topics.room

import com.tuxoo.too_memo.model.topics.TopicsRepository
import com.tuxoo.too_memo.model.topics.entity.Topic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomTopicsRepository(
    private val topicsDao: TopicsDao
) : TopicsRepository {

    override suspend fun getAll(): Flow<List<Topic>> =
        topicsDao.findAll().map {
            it.map { entity ->
                entity.toTopic()
            }
        }

    override suspend fun add(topic: Topic) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(topic: Topic) {
        topicsDao.delete(topic.toTopicEntity())
    }

    override suspend fun update(topic: Topic) {
        topicsDao.update(topic.toTopicEntity())
    }
}