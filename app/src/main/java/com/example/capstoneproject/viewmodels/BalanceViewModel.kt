package com.example.capstoneproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.capstoneproject.repository.InAndOutRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BalanceViewModel(application: Application) : AndroidViewModel(application){

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val repository = InAndOutRepository(application.applicationContext)

    //The number is changing each time when the user adds anything in the database.
    val totalBalance  = MutableLiveData<Double>(0.0)
    val totalSavings = MutableLiveData<Double>(0.0)

    //initializing the GetBalance and GetSavings
    init {
        getBalance()
        getSavings()
    }

    fun getBalance() {
        CoroutineScope(Dispatchers.Main).launch{
            totalBalance.value = repository.getBalance()
        }
    }

    fun getSavings() {
        CoroutineScope(Dispatchers.Main).launch{
            totalSavings.value = repository.getSavings()
        }
    }

}