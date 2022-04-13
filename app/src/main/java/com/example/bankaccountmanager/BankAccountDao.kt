package com.example.bankaccountmanager

import androidx.room.*

@Dao
interface BankAccountDao {
    @Query ("SELECT * FROM BankAccount WHERE cardNumber IN (:userCardNumber) ")
    fun getBankAccountInfo(userCardNumber:Int): BankAccount

    @Query("SELECT * FROM BankAccount")
    fun getAll():List<BankAccount>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg bankAccountInfo: BankAccount)

    @Insert
    fun addAccount( bankAccount: BankAccount)

//    @Delete
//    fun delete(bankAccountInfo: BankAccount )
      @Query("DELETE FROM BankAccount")
       fun delete()
}
