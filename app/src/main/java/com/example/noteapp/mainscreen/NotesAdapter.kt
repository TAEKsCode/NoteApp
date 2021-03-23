package com.example.noteapp.mainscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.database.Note
import com.example.noteapp.databinding.ListItemNoteBinding

class NotesAdapter(
    private val clickListener: NoteClickListener,
    private val deleteListener: NoteDeleteListener
) :
    ListAdapter<Note, NotesAdapter.ViewHolder>(NotesDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener, deleteListener)
    }

    class ViewHolder private constructor(private val binding: ListItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Note, clickListener: NoteClickListener, deleteListener: NoteDeleteListener) {
            binding.note = item
            binding.clickListeneer = clickListener
            binding.deleteListener = deleteListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemNoteBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }
}

class NotesDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.noteId == newItem.noteId
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

}

class NoteClickListener(val clickListener: (noteId: Long) -> Unit) {
    fun onClick(note: Note) = clickListener(note.noteId)
}

class NoteDeleteListener(val clickListener: (noteId: Long) -> Unit) {
    fun onDeleteClick(note: Note) = clickListener(note.noteId)
}