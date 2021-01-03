package com.example.capstoneproject.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.capstoneproject.model.Income

/**
 * The entities of the table.
 */

@Dao
interface IncomeDao {
    @Query("SELECT * FROM incomeTable ORDER BY date DESC")
    fun getAllIncomes(): LiveData<List<Income>>

    @Query("SELECT SUM(amount) FROM incomeTable limit 1")
    suspend fun getSumIncomes(): Double

    @Insert
    suspend fun insertIncome(income: Income)

    @Delete
    suspend fun deleteIncome(income: Income)

    @Query("DELETE FROM incomeTable")
    suspend fun deleteAllIncomes()
}