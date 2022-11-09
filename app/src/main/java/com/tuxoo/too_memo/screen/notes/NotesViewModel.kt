package com.tuxoo.too_memo.screen.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuxoo.too_memo.model.topics.TopicsRepository
import com.tuxoo.too_memo.model.topics.entity.Topic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NotesViewModel(
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

    fun delete(topic: Topic) {
        viewModelScope.launch {
            topicsRepository.delete(topic)
        }
    }
}