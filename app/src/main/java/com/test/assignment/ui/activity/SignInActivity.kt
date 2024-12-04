package com.test.assignment.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.test.assignment.R
import com.test.assignment.databinding.ActivitySignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_sign_in)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        //setSupportActionBar(binding.toolBar)
        // Setting up a back button
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_signin) as NavHostFragment
        navController = navHostFragment.navController
        //setupActionBarWithNavController(navController)
        navController.setGraph(R.navigation.navigation_login, Bundle())
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // API 5+ solution
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> return navController.navigateUp()
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }
}