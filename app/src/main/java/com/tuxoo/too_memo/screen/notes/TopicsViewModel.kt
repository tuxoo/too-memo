package com.tuxoo.too_memo.screen.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuxoo.too_memo.model.topics.TopicsRepository
import com.tuxoo.too_memo.model.topics.entity.Topic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TopicsViewModel(
    private val topicsRepository: TopicsRepository
) : ViewModel() {

    private val _topics = MutableStateFlow<List<Topic>>(emptyList())
    val topics: StateFlow<List<Topic>> = _topics

    init {
        viewModelScope.launch {
            // TODO: exception
            topicsRepository.getAll()
                .flowOn(Dispatchers.IO)
                .collect {
                    _topics.value = it
                }
        }
    }

    fun deleteTopic(topic: Topic) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                topicsRepository.delete(topic)
            }
        }
    }

    fun pinTopic(topic: Topic, pin: Boolean) {
        viewModelScope.launch {
            val pinnedTopic = topic.copy(isPinned = pin)
            withContext(Dispatchers.IO) {
                topicsRepository.update(pinnedTopic)
            }
        }
    }
}