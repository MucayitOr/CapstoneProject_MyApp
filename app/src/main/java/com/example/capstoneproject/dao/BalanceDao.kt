package com.example.capstoneproject.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.capstoneproject.model.Expense

@Dao
interface BalanceDao {
    @Query("SELECT ((SELECT sum(amount)FROM incomeTable) - (SELECT sum(amount) FROM expensesTable))")
    suspend fun getBalance(): Double

    @Query("SELECT sum(amount) From expensesTable WHERE title = 'Savings'")
    suspend fun getSavings(): Double

}