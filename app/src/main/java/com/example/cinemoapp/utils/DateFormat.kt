package com.example.cinemoapp.utils

import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class DateFormat(private val date: String?) {

    fun dateConvert():String{
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)
        val date: Date = inputFormat.parse(date) ?: Date()
        return outputFormat.format(date)
    }
}