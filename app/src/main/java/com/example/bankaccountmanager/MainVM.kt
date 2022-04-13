package com.example.bankaccountmanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MainVM(app: Application): AndroidViewModel(app) {
    init {
        Repository.initDB(app.applicationContext)
    }
    fun delete(){
        Repository.delete()
    }
}