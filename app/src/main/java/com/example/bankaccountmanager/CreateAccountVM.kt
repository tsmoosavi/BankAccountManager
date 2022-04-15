package com.example.bankaccountmanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class CreateAccountVM(app: Application): AndroidViewModel(app) {
//    var accountType = ""
//    var cardNumber = 0
//    var balance = 0.0
    lateinit var x: LiveData<Int>


    init {
        Repository.initDB(app.applicationContext)
    }
    fun addAccount(bankInfo: BankAccount){
        Repository.addAccount(bankInfo)
    }
    fun getList(){
        Repository.getAccounts()
    }

}