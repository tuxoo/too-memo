package com.tuxoo.too_memo.model.topics.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tuxoo.too_memo.model.topics.room.entity.TopicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopicsDao {

    @Query("SELECT * FROM topics")
    fun findAll(): Flow<List<TopicEntity>>

    @Insert
    suspend fun save(topic: TopicEntity)
}