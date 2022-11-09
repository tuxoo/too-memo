package com.tuxoo.too_memo.screen.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuxoo.too_memo.databinding.ItemNoteBinding
import com.tuxoo.too_memo.model.notes.entity.Note
import com.tuxoo.too_memo.util.DateTimeUtil.dateFormatter

class NotesAdapter() : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    var notes: List<Note> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = notes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)

        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]

        with(holder.binding) {
            titleTextView.text = note.title
            descriptionTextView.text = note.description
            editedAtNoteTextView.text = dateFormatter.format(note.lastModified)
        }
    }

    class NoteViewHolder(
        val binding: ItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root)
}