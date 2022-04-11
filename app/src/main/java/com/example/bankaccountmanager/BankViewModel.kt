package com.example.bankaccountmanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class BankViewModel(app: Application):AndroidViewModel(app) {
    init {
        Repository.initDB(app.applicationContext)
    }
}