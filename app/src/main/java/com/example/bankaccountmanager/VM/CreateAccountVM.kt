package com.example.bankaccountmanager.VM

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankaccountmanager.database.BankAccount
import com.example.bankaccountmanager.database.Repository


class CreateAccountVM(app: Application) : AndroidViewModel(app) {
    var userNumberOfAccounts = 0
    var countOfAccounts: LiveData<Int>
    var registerButtonEnable = MutableLiveData<Boolean>(true)

    init {
        Repository.initDB(app.applicationContext)
        countOfAccounts = Repository.getListSize()
    }

    fun addAccount(bankInfo: BankAccount) {
        Repository.addAccount(bankInfo)
    }

    //    fun getList(){
//        Repository.getAccounts()
//    }
    fun checkNumberOfAddAccounts() {
        if (countOfAccounts.value == userNumberOfAccounts) {
            registerButtonEnable.value = false
        }
    }

    fun checkRepeat(cardNumber: Int): Boolean {
        if (Repository.getAccountInfo(cardNumber).number == null) {
            return false
        }
        return true
    }

}
