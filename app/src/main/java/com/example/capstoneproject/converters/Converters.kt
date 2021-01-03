package com.example.capstoneproject.converters

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {

    @TypeConverter
    fun dateToLong(date: LocalDate?): String? {
        return date.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun stringToDate(date: String?): LocalDate? {
        val dateArray = date?.split('-')
        return LocalDate.of(dateArray?.get(0)!!.toInt(), dateArray[1].toInt(), dateArray[2].toInt())
    }

}