package com.example.bankaccountmanager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BankAccount::class],version = 1)
abstract class BankDatabase : RoomDatabase(){
    abstract fun bankAccountDao(): BankAccountDao
    companion object {
        var INSTANCE: BankDatabase? = null

        fun getAppDataBase(context: Context): BankDatabase? {
            if (INSTANCE == null){
                synchronized(BankDatabase::class){
                    INSTANCE =
                        Room.databaseBuilder(context.applicationContext,
                            BankDatabase::class.java, "myDB")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}