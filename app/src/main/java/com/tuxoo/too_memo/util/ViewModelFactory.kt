package com.tuxoo.too_memo.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tuxoo.too_memo.model.notes.NotesRepository
import com.tuxoo.too_memo.model.topics.TopicsRepository
import com.tuxoo.too_memo.screen.notes.NotesViewModel
import com.tuxoo.too_memo.screen.notes.TopicsViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val topicsRepository: TopicsRepository,
    private val notesRepository: NotesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            TopicsViewModel::class.java -> TopicsViewModel(topicsRepository)
            NotesViewModel::class.java -> NotesViewModel(notesRepository)
            else -> error("Unknown view model class")
        }

        return viewModel as T
    }
}