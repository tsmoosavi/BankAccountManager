package com.example.bankaccountmanager

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.bankaccountmanager.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.BaseTransientBottomBar

class MainActivity : AppCompatActivity() {
    val vm:MainVM by viewModels()
    private lateinit var navController : NavController
    private lateinit var drawerLayout : DrawerLayout
    private lateinit var appBarConfiguration : AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var  navigationView = findViewById<NavigationView>(R.id.navigationView)

        navController = findNavController(R.id.firstFragment)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView.setupWithNavController(navController)

//        appBarConfiguration = AppBarConfiguration(navController.graph,drawerLayout)
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.profileFragment, R.id.createAccountFragment,
            R.id.showAccountFragment, R.id.selectAccountFragment,
            R.id.deleteAccount
        )
            ,drawerLayout)
        setupActionBarWithNavController(navController,appBarConfiguration)
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.deleteAccount ->{
                    deleteAccounts(this)
                }
            }
            NavigationUI.onNavDestinationSelected(it, navController)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.firstFragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
    fun deleteAccounts(activity: Activity) {
        val builder: AlertDialog.Builder = activity.let {
            AlertDialog.Builder(it)
        }
        builder
            .setMessage("Are you sure about delete accounts?")
            .setTitle("DELETE ?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog, id->
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show()
                vm.delete()
            })
            .setNegativeButton("No",DialogInterface.OnClickListener{ dialog,id-> })
            .setCancelable(false)
            .show()
    }
}