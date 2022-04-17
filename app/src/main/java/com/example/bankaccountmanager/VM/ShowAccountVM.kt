package com.example.bankaccountmanager.VM

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.bankaccountmanager.database.BankAccount
import com.example.bankaccountmanager.database.Repository

class ShowAccountVM (app: Application): AndroidViewModel(app){

    val numberLiveData= MutableLiveData<Int>(0)
    init {
        Repository.initDB(app.applicationContext)
    }

    val bankAccountLiveData = MutableLiveData<BankAccount>()
    var ListSize =(Repository.getListSize())


//    fun next(){
//        numberLiveData.value = numberLiveData.value?.plus(1)
//        numberLiveData.value?.let { number ->
//            bankAccountLiveData.value = accountList.value?.get(number)
//        }
//    }
//    fun backClicked(){
//        numberLiveData.value = numberLiveData.value?.minus(1)
//        numberLiveData.value?.let{number->
//            bankAccountLiveData.value =accountList.value?.get(number)
//        }
//    }

}
