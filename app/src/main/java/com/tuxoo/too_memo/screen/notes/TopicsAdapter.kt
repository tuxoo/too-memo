package com.tuxoo.too_memo.screen.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuxoo.too_memo.databinding.ItemTopicBinding
import com.tuxoo.too_memo.model.topics.entity.Topic

class TopicsAdapter : RecyclerView.Adapter<TopicsAdapter.TopicViewHolder>() {

    var topics: List<Topic> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = topics.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTopicBinding.inflate(inflater, parent, false)

        return TopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val topicsItem = topics[position]

        with(holder.binding) {
            topicNameTextView.text = topicsItem.name
            createdAtTopicTextView.text = topicsItem.createdAt
        }
    }

    class TopicViewHolder(
        val binding: ItemTopicBinding
    ) : RecyclerView.ViewHolder(binding.root)
}