package com.example.capstoneproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstoneproject.model.Income
import com.example.capstoneproject.repository.InAndOutRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IncomeViewModel(application: Application) : AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val incomeRepository = InAndOutRepository(application.applicationContext)

    val incomes: LiveData<List<Income>> = incomeRepository.getAllIncomes()
    //The number is changing each time when the user adds anything in the database.
    val totalIncomes  = MutableLiveData<Double>(0.0)

    //initializing the GetSumIncomes
    init {
        getSumIncomes()
    }

    fun getSumIncomes() {
        CoroutineScope(Dispatchers.Main).launch{
            totalIncomes.value = incomeRepository.getSumIncomes()
        }
    }

    fun insertIncome(income: Income) {
        ioScope.launch {
            incomeRepository.insertIncome(income)
        }
    }

    fun deleteIncome(income: Income) {
        ioScope.launch {
            incomeRepository.deleteIncome(income)
        }
    }

    fun deleteAllIncomes(){
        ioScope.launch {
            incomeRepository.deleteAllIncomes()
        }
    }
}