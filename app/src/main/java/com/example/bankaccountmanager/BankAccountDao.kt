package com.example.bankaccountmanager

import androidx.room.*

@Dao
interface BankAccountDao {
    @Query ("SELECT * FROM BankAccount")
    fun getBankAccountInfo(): BankAccount

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg bankAccountInfo: BankAccount)

    @Delete
    fun delete(bankAccountInfo: BankAccount )
}
