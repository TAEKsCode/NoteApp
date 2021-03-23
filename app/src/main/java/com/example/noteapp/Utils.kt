package com.example.noteapp

import android.annotation.SuppressLint
import java.text.SimpleDateFormat


    @SuppressLint("SimpleDateFormat")
    fun convertMillisToDate(millis: Long): String {
        return SimpleDateFormat("dd.MM yyyy").format(millis)
    }
