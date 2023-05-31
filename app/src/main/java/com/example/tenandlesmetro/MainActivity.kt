package com.example.tenandlesmetro

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.tenandlesmetro.databinding.ActivityMainBinding
import com.example.tenandlesmetro.utils.hideNavBar

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        navController = this.findNavController(R.id.MyHostNavFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
        val view: View = findViewById(android.R.id.content)
        view.hideNavBar()

        window.navigationBarColor = getColor(R.color.backColor)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.MyHostNavFragment)
        return navController.navigateUp()
    }

}