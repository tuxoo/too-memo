package com.tuxoo.too_memo.screen.notes

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tuxoo.too_memo.R
import com.tuxoo.too_memo.databinding.ItemTopicBinding
import com.tuxoo.too_memo.model.topics.entity.Topic
import com.tuxoo.too_memo.util.DateTimeUtil.dateFormatter

interface TopicActionListener {

    fun onTopicPinned(topic: Topic)

    fun onTopicDelete(topic: Topic)

    fun onTopicNotes(topic: Topic)
}

class TopicDiffCallBack(
    private val oldList: List<Topic>,
    private val newList: List<Topic>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}

class TopicsAdapter(
    private val actionListener: TopicActionListener
) : RecyclerView.Adapter<TopicsAdapter.TopicViewHolder>(), View.OnClickListener {

    var topics: List<Topic> = emptyList()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(TopicDiffCallBack(field, value))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onClick(v: View): Unit =
        (v.tag as Topic).run {
            when (v.id) {
                R.id.moreImageViewButton -> showPopupMenu(v)
                else -> actionListener.onTopicNotes(this)
            }
        }

    override fun getItemCount(): Int = topics.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTopicBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.moreImageViewButton.setOnClickListener(this)

        return TopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val topic = topics[position]

        with(holder.binding) {
            holder.itemView.tag = topic
            moreImageViewButton.tag = topic

            topicNameTextView.text = topic.name
            createdAtTopicTextView.text = dateFormatter.format(topic.createdAt)

            pinImageView.visibility = if (topic.isPinned) View.VISIBLE
            else View.INVISIBLE
        }
    }

    private fun showPopupMenu(v: View) {
        val popupMenu = PopupMenu(v.context, v)
        val context = v.context
        val topic = v.tag as Topic

        if (topic.isPinned) {
            popupMenu.menu.add(0, ID_PIN, Menu.NONE, context.getString(R.string.unpin))
        } else {
            popupMenu.menu.add(0, ID_PIN, Menu.NONE, context.getString(R.string.pin))
        }

        popupMenu.menu.add(0, ID_DELETE, Menu.NONE, context.getString(R.string.delete))

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                ID_PIN -> actionListener.onTopicPinned(topic)
                ID_DELETE -> actionListener.onTopicDelete(topic)
            }
            return@setOnMenuItemClickListener true
        }

        popupMenu.show()
    }

    class TopicViewHolder(
        val binding: ItemTopicBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val ID_PIN = 1
        private const val ID_DELETE = 2
    }
}