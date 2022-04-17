package com.example.bankaccountmanager.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.bankaccountmanager.BankDatabase

object Repository { //class
    var db : BankDatabase? = null
    var bankDao: BankAccountDao? = null

    fun initDB(context: Context){
        db = BankDatabase.getAppDataBase(context)
        bankDao = db?.bankAccountDao()
    }
//    fun getAccounts():LiveData<List<BankAccount>> {
//        return db!!.bankAccountDao().getAll()
//    }
    fun addAccount(bA: BankAccount){
        db!!.bankAccountDao().addAccount(bA)
    }
    fun getListSize():LiveData<Int>{
       return db!!.bankAccountDao().getItemsNumber()
    }
    fun delete(){
        db!!.bankAccountDao().delete()
    }
    fun getAccountInfo(number:Int):BankAccount{
       return db!!.bankAccountDao().getBankAccountInfo(number)
    }
    fun getAccountByNumber(id:Int):BankAccount{
       return db!!.bankAccountDao().getOneBankAccount(id)
    }
}