package com.example.bankaccountmanager.VM

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.bankaccountmanager.database.BankAccount
import com.example.bankaccountmanager.database.Repository

class SelectVM(app: Application): AndroidViewModel(app) {
    init {
        Repository.initDB(app.applicationContext)
    }

    fun findAccount(cardNumber:Int): BankAccount {
        return Repository.getAccountInfo(cardNumber)
    }


    fun checkRepeat(cardNumber: Int): Boolean {
        if (Repository.getAccountInfo(cardNumber) == null) {
            return false
        }
        return true
    }
}