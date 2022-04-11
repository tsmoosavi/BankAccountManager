package com.example.bankaccountmanager

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BankAccount(
    @PrimaryKey(autoGenerate = true) val number: Int,
    var accountType : String,
    var cardNumber : String,
    var balance : String
)