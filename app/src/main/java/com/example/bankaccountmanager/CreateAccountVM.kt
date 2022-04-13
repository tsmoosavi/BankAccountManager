package com.example.bankaccountmanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class CreateAccountVM(app: Application): AndroidViewModel(app) {
    var accountType = ""
    var cardNumber = 0
    var balance = 0.0
    var x = MutableLiveData<String>("")
    init {
        Repository.initDB(app.applicationContext)
    }
    fun addAccount(bankInfo: BankAccount){
        Repository.addAccount(bankInfo)
    }
    fun getList(){
        Repository.getList()
    }
    fun getSize():LiveData<Int>{
        return Repository.getSize()
        x = MutableLiveData<String> (return Repository.getSize())
    }
//     var x = MutableLiveData<String> (Repository.getList().value?.size.toString()).toString())
}