package com.example.capstoneproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.capstoneproject.converters.Converters
import com.example.capstoneproject.dao.BalanceDao
import com.example.capstoneproject.dao.ExpensesDao
import com.example.capstoneproject.dao.IncomeDao
import com.example.capstoneproject.model.Expense
import com.example.capstoneproject.model.Income

@Database(entities = [Income::class , Expense::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class InAndOutRoomDatabase : RoomDatabase() {

    //The tables (income, expenses and the balance)
    abstract fun incomeDao(): IncomeDao
    abstract fun expensesDao(): ExpensesDao
    abstract fun balanceDao(): BalanceDao

    companion object {
        private const val DATABASE_NAME = "INCOME_AND_EXPENSES_DATABASE"

        @Volatile
        private var INSTANCE: InAndOutRoomDatabase? = null

        fun getDatabase(context: Context): InAndOutRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(InAndOutRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            InAndOutRoomDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE
        }
    }

}