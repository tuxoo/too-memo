package com.tuxoo.too_memo.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tuxoo.too_memo.model.topics.TopicsRepository
import com.tuxoo.too_memo.screen.notes.NotesViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val topicsRepository: TopicsRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            NotesViewModel::class.java -> NotesViewModel(topicsRepository)
            else -> error("Unknown view model class")
        }

        return viewModel as T
    }
}