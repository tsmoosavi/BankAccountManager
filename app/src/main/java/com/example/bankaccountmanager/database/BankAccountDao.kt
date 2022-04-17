package com.example.bankaccountmanager.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BankAccountDao {
    @Query ("SELECT * FROM BankAccount WHERE cardNumber = :userCardNumber ")
    fun getBankAccountInfo(userCardNumber:Int):LiveData<BankAccount>

    @Query("SELECT * FROM BankAccount")
    fun getAll():LiveData<List<BankAccount>>


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
