package com.example.bankaccountmanager

import android.content.Context

object Repository {
    var db : BankDatabase? = null
    var bankDao: BankAccountDao? = null

    fun initDB(context: Context){
        db = BankDatabase.getAppDataBase(context)
        bankDao =db?.bankAccountDao()
    }
    fun getAccounts():List<BankAccount>{
        return db!!.bankAccountDao().getAll()
    }
}