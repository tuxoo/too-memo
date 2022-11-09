package com.tuxoo.too_memo.screen.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuxoo.too_memo.databinding.FragmentNotesBinding
import com.tuxoo.too_memo.model.topics.entity.Topic
import com.tuxoo.too_memo.util.ViewModelFactory
import com.tuxoo.too_memo.util.appComponent
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesBinding
    private lateinit var topicsAdapter: TopicsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: NotesViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesBinding.inflate(inflater, container, false)

        requireContext().appComponent.inject(this)

        topicsAdapter = TopicsAdapter(object : TopicActionListener {
            override fun onTopicPinned(topic: Topic) {

            }

            override fun onTopicDelete(topic: Topic) {
                viewModel.delete(topic)
            }

            override fun onTopicNotes(topic: Topic) {
                Toast.makeText(
                    this@NotesFragment.requireContext(),
                    "Topic: ${topic.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.viewModelScope.launch {
            viewModel.topics.collect {
                topicsAdapter.topics = it
            }
        }

        binding.topicsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.topicsRecyclerView.adapter = topicsAdapter

        return binding.root
    }

    companion object {

        fun newInstance(): NotesFragment = NotesFragment()
    }
}