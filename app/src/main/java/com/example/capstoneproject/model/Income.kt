package com.example.capstoneproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

/**
 * The entities of the table.
 */

@Entity(tableName = "incomeTable")
data class Income(

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "description")
        var description: String,

        @ColumnInfo(name = "amount")
        var amount: Double,

        @ColumnInfo(name = "date")
        var date: LocalDate,

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Long? = null
)