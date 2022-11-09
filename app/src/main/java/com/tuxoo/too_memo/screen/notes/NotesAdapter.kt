package com.tuxoo.too_memo.screen.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tuxoo.too_memo.R
import com.tuxoo.too_memo.databinding.ItemNoteBinding
import com.tuxoo.too_memo.model.notes.entity.Note
import com.tuxoo.too_memo.model.topics.entity.Topic
import com.tuxoo.too_memo.util.DateTimeUtil.dateFormatter

interface NoteActionListener {

    fun onNoteDelete(note: Note)
}

class NotesDiffCallBack(
    private val oldList: List<Note>,
    private val newList: List<Note>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}

class NotesAdapter(
    private val actionListener: NoteActionListener
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>(), View.OnClickListener {

    var notes: List<Note> = emptyList()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(NotesDiffCallBack(field, value))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onClick(v: View): Unit =
        (v.tag as Note).run {
            when (v.id) {
                R.id.deleteNoteImageViewButton -> {
                    actionListener.onNoteDelete(this)
                }
                R.id.editedAtNoteTextView -> {

                }
                else -> error("")
            }
        }

    override fun getItemCount(): Int = notes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)

        binding.deleteNoteImageViewButton.setOnClickListener(this)

        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]

        with(holder.binding) {
            deleteNoteImageViewButton.tag = note
            editNoteImageViewButton.tag = note

            titleTextView.text = note.title
            descriptionTextView.text = note.description
            editedAtNoteTextView.text = dateFormatter.format(note.lastModified)
        }
    }

    class NoteViewHolder(
        val binding: ItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root)
}