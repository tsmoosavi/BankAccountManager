package com.example.bankaccountmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.bankaccountmanager.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
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
                    Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show()
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
}