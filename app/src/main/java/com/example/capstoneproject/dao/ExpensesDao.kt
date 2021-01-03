package com.example.capstoneproject.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.capstoneproject.model.Expense

/**
 * The entities of the table.
 */

@Dao
interface ExpensesDao {
    @Query("SELECT * FROM expensesTable ORDER BY date DESC")
    fun getAllExpenses(): LiveData<List<Expense>>

    @Query("SELECT SUM(amount) FROM expensesTable limit 1")
    suspend fun getSumExpense(): Double
    
    @Insert
    suspend fun insertExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query("DELETE FROM expensesTable")
    suspend fun deleteAllExpenses()
}