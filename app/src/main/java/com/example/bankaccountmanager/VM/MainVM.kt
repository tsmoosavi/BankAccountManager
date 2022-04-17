package com.example.bankaccountmanager.VM

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.bankaccountmanager.database.Repository

class MainVM(app: Application): AndroidViewModel(app) {
    init {
        Repository.initDB(app.applicationContext)
    }
    fun delete(){
        Repository.delete()
    }
}