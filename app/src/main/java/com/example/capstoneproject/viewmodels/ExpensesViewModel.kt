package com.example.capstoneproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstoneproject.model.Expense
import com.example.capstoneproject.repository.InAndOutRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpensesViewModel(application: Application) : AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val repository = InAndOutRepository(application.applicationContext)

    val expenses: LiveData<List<Expense>> = repository.getAllExpenses()
    //The number is changing each time when the user adds anything in the database.
    val totalExpenses  = MutableLiveData<Double>(0.0)

    //initializing the GetSumExpenses
    init {
        getSumExpenses()
    }

    fun getSumExpenses()  {
        CoroutineScope(Dispatchers.Main).launch {
            totalExpenses.value = repository.getSumExpenses()
        }
    }

    fun insertExpense(expense: Expense) {
        ioScope.launch {
            repository.insertExpense(expense)
        }
    }

    fun deleteExpense(expense: Expense) {
        ioScope.launch {
            repository.deleteExpense(expense)
        }
    }

    fun deleteAllExpenses(){
        ioScope.launch {
            repository.deleteAllExpenses()
        }
    }
}