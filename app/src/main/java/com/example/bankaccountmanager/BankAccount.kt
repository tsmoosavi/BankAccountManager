package com.example.bankaccountmanager

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BankAccount(
    @PrimaryKey(autoGenerate = true) var number :Int,
    var accountType : String,
     var cardNumber : Int,
    var balance : Double
)