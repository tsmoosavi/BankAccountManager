package com.example.bankaccountmanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class BankViewModel(app: Application):AndroidViewModel(app) {
   var bankAccountList: MutableLiveData<List<BankAccount>>
    var ListSize = MutableLiveData<String>("")
    var bankAccountInfo = MutableLiveData<BankAccount>()
    init {
        Repository.initDB(app.applicationContext)
        bankAccountList = Repository.getAccounts() as MutableLiveData<List<BankAccount>>
        bankAccountInfo.value = bankAccountList.value?.get(0)


    }
}

