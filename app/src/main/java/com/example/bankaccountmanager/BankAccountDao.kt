package com.example.bankaccountmanager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface BankAccountDao {
    @Query ("SELECT * FROM BankAccount WHERE cardNumber IN (:userCardNumber) ")
    fun getBankAccountInfo(userCardNumber:Int):MutableLiveData< BankAccount>

    @Query("SELECT * FROM BankAccount")
    fun getAll(): MutableLiveData<List<BankAccount>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg bankAccountInfo: BankAccount)

    @Query("SELECT  count(number) FROM BankAccount")
    fun getItemsNumber(): LiveData<Int>

    @Insert
    fun addAccount( bankAccount: BankAccount)

//    @Delete
//    fun delete(bankAccountInfo: BankAccount )
      @Query("DELETE FROM BankAccount")
       fun delete()
}
