package com.tuxoo.too_memo.screen.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuxoo.too_memo.model.topics.TopicsRepository
import com.tuxoo.too_memo.model.topics.entity.Topic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TopicsViewModel(
    private val topicsRepository: TopicsRepository
) : ViewModel() {

    private val _topics = MutableStateFlow<List<Topic>>(emptyList())
    val topics: StateFlow<List<Topic>> = _topics

    init {
        viewModelScope.launch {
            topicsRepository.getAll().collect {
                _topics.value = it
            }
        }
    }

    fun deleteTopic(topic: Topic) {
        viewModelScope.launch {
            topicsRepository.delete(topic)
        }
    }

    fun pinTopic(topic: Topic, pin: Boolean) {
        viewModelScope.launch {
            val pinnedTopic = topic.copy(isPinned = pin)
            topicsRepository.update(pinnedTopic)
        }
    }
}