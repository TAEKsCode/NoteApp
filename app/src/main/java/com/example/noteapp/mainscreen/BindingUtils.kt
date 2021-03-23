package com.example.noteapp.mainscreen

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.noteapp.convertMillisToDate
import com.example.noteapp.database.Note

@BindingAdapter("noteText")
fun TextView.setNoteText(item: Note?) {
    item?.let {
        text = item.noteText
    }
}

@BindingAdapter("noteDate")
fun TextView.setNoteDate(item: Note?) {
    item?.let {
        text = convertMillisToDate(item.noteDateMilli)
    }
}

