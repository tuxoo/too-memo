package com.tuxoo.too_memo.screen.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuxoo.too_memo.model.notes.NotesRepository
import com.tuxoo.too_memo.model.notes.entity.Note
import com.tuxoo.too_memo.model.topics.entity.Topic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesViewModel(
    private val notesRepository: NotesRepository
) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    fun getByTopic(topic: Topic) {
        viewModelScope.launch {
            // TODO: exception
            notesRepository.getByTopic(topic)
                .flowOn(Dispatchers.IO)
                .collect {
                    _notes.value = it
                }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                notesRepository.delete(note)
            }
        }
    }

    fun sortByAlphabet() {
        viewModelScope.launch {
            notes.map {
                it.sortedBy { note -> note.title }
            }.collect {
                _notes.value = it
            }
        }
    }
}