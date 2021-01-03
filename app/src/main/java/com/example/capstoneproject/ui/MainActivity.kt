package com.example.capstoneproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.capstoneproject.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.capstoneproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigation(findNavController(R.id.nav_host_fragment))

    }

    /**
     *The navigation will be done by the the function setNavigation. Different buttons are set to
     * their destination (fragment/page).
     */
    private fun setNavigation(navController: NavController) {
        val navView: BottomNavigationView = binding.bottomNav

        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mi_home -> {
                    navController.navigate(R.id.homeFragment)
                }
                R.id.mi_expenses -> {
                    navController.navigate(R.id.expensesOverviewFragment)
                }
                R.id.mi_incomes -> {
                    navController.navigate(R.id.FirstFragment)
                }
            }
            true
        }
    }
}