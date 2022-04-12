package com.example.bankaccountmanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class BankViewModel(app: Application):AndroidViewModel(app) {
    lateinit var bankAccountList: List<BankAccount>
    var bankAccountInfo = MutableLiveData<BankAccount>()
    init {
        Repository.initDB(app.applicationContext)
        bankAccountList = Repository.getAccounts()
        bankAccountInfo.value = bankAccountList[0]

    }
}