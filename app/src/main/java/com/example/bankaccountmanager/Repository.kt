package com.example.bankaccountmanager

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Repository {
    var db : BankDatabase? = null
    var bankDao: BankAccountDao? = null

    fun initDB(context: Context){
        db = BankDatabase.getAppDataBase(context)
        bankDao =db?.bankAccountDao()
    }
    fun getAccounts():MutableLiveData<List<BankAccount>> {
        return db!!.bankAccountDao().getAll()
    }
    fun addAccount(bA:BankAccount){
        db!!.bankAccountDao().addAccount(bA)
    }
    fun getListSize():LiveData<Int>{
       return db!!.bankAccountDao().getItemsNumber()
    }
    fun delete(){
        db!!.bankAccountDao().delete()
    }
}