package com.example.capstoneproject.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.capstoneproject.dao.BalanceDao
import com.example.capstoneproject.dao.ExpensesDao
import com.example.capstoneproject.dao.IncomeDao
import com.example.capstoneproject.database.InAndOutRoomDatabase
import com.example.capstoneproject.model.Expense
import com.example.capstoneproject.model.Income

class InAndOutRepository(context: Context) {
    private var incomeDao: IncomeDao
    private var expensesDao: ExpensesDao
    private var balanceDao: BalanceDao

    init {
        val incomeRoomDatabase = InAndOutRoomDatabase.getDatabase(context)
        incomeDao = incomeRoomDatabase!!.incomeDao()
        expensesDao = incomeRoomDatabase!!.expensesDao()
        balanceDao = incomeRoomDatabase!!.balanceDao()
    }

    //Income repository

    fun getAllIncomes(): LiveData<List<Income>> {
        return incomeDao.getAllIncomes()
    }

    suspend fun getSumIncomes(): Double {
        return incomeDao.getSumIncomes()
    }

    suspend fun insertIncome(income: Income) {
        incomeDao.insertIncome(income)
    }

    suspend fun deleteIncome(income: Income) {
        incomeDao.deleteIncome(income)
    }

    suspend fun  deleteAllIncomes(){
        incomeDao.deleteAllIncomes()
    }

    //Expenses repository
    fun getAllExpenses(): LiveData<List<Expense>>{
        return expensesDao.getAllExpenses()
    }

    suspend fun getSumExpenses(): Double {
        return expensesDao.getSumExpense()
    }

    suspend fun insertExpense(expense: Expense) {
        expensesDao.insertExpense(expense)
    }

    suspend fun deleteExpense(expense: Expense) {
        expensesDao.deleteExpense(expense)
    }

    suspend fun  deleteAllExpenses(){
        expensesDao.deleteAllExpenses()
    }

    //Balance repository

    suspend fun getBalance(): Double {
        return balanceDao.getBalance()
    }

    suspend fun getSavings(): Double {
        return balanceDao.getSavings()
    }
}