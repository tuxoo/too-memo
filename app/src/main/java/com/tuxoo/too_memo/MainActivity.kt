package com.tuxoo.too_memo

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.tuxoo.too_memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigation: NavigationView
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (drawerToggle.onOptionsItemSelected(item)) {
            true
        }
        else {
            super.onOptionsItemSelected(item)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout
        navigation = binding.navigation
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigation.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> Toast.makeText(this, "Home Selected", Toast.LENGTH_SHORT).show()
                R.id.topics -> Toast.makeText(this, "Topics Selected", Toast.LENGTH_SHORT).show()
                R.id.about -> Toast.makeText(this, "About Selected", Toast.LENGTH_SHORT).show()
                R.id.login -> Toast.makeText(this, "Login Selected", Toast.LENGTH_SHORT).show()
            }
            false
        }

//        if (savedInstanceState == null) {
//            supportFragmentManager
//                .beginTransaction()
//                .add(R.id.fragmentContainer, TopicsFragment())
//                .commit()
//        }
    }
}