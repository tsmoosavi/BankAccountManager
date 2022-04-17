package com.example.bankaccountmanager.VM

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankaccountmanager.database.BankAccount
import com.example.bankaccountmanager.database.Repository

class ShowAccountVM (app: Application): AndroidViewModel(app){

    val numberLiveData= MutableLiveData<Int>(1)
    var countAccountsNumberLD : LiveData<Int>
    lateinit var bALD:MutableLiveData<BankAccount>
    var nextEnableLD =MutableLiveData<Boolean>(true)
    var backEnableLD =MutableLiveData<Boolean>(false)
    init {
        Repository.initDB(app.applicationContext)
        bALD.value = Repository.getAccountByNumber(1)
        countAccountsNumberLD = Repository.getListSize()
    }
//    fun findAccount(cardNumber:Int):BankAccount{
//       return Repository.getAccountInfo(cardNumber)
//    }
//
//    fun checkRepeat(cardNumber: Int): Boolean {
//        if (Repository.getAccountInfo(cardNumber) == null) {
//            return false
//        }
//        return true
//    }

    fun next(){
        backEnableLD.value = true
        numberLiveData.value = numberLiveData.value?.plus(1)
//        bALD.value = numberLiveData.value?.let { Repository.getAccountByNumber(it) }
        numberLiveData.value?.let{number->
            bALD.value = Repository.getAccountByNumber(number)
        }
        if (numberLiveData.value == countAccountsNumberLD.value){
            nextEnableLD.value = false
        }
    }
    fun back(){
        nextEnableLD.value = true
        numberLiveData.value = numberLiveData.value?.minus(1)
//        bALD.value = numberLiveData.value?.let { Repository.getAccountByNumber(it) }
        numberLiveData.value?.let{number->
            bALD.value = Repository.getAccountByNumber(number)
        }
        if (numberLiveData.value ==1){
            backEnableLD.value = false
        }
    }

}
