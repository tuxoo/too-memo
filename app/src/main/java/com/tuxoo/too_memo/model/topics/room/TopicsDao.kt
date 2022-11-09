package com.tuxoo.too_memo.model.topics.room

import androidx.room.*
import com.tuxoo.too_memo.model.topics.room.entity.TopicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopicsDao {

    @Query("SELECT * FROM topics ORDER BY is_pinned DESC, created_at DESC")
    fun findAll(): Flow<List<TopicEntity>>

    @Insert
    suspend fun save(topic: TopicEntity)

    @Delete
    suspend fun delete(topic: TopicEntity)

    @Update
    suspend fun update(topic: TopicEntity)
}