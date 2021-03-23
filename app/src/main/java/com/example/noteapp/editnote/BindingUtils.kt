package com.example.noteapp.editnote

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.noteapp.convertMillisToDate
import com.example.noteapp.database.Note

@BindingAdapter("setNoteTextToEdit")
fun EditText.setNoteText(item: Note?){
    item?.let {
        setText(item.noteText)
    }
}

@BindingAdapter("noteDate")
fun TextView.setNoteDate(item: Note?) {
    item?.let {
        text = convertMillisToDate(item.noteDateMilli)
    }
}