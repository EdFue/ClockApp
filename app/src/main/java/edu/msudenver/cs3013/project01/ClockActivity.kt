package edu.msudenver.cs3013.project01

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class ClockActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clock)

        val name = intent.getStringExtra("name")
        //toast message
        Toast.makeText(this, "Hello $name!", Toast.LENGTH_LONG).show()

        setSupportActionBar(findViewById(R.id.toolbar))

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        //Creating top level destinations
        //and adding them to the draw
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.worldClockFragment, R.id.stopwatchFragment, R.id.timerFragment, R.id.alarmFragment
            ), findViewById(R.id.drawer_layout)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        val navViewDrawer = findViewById<NavigationView>(R.id.nav_view_drawer)
        navViewDrawer?.setupWithNavController(navController)

        // Setup BottomNavigationView
        val navViewBottom = findViewById<BottomNavigationView>(R.id.nav_view)
        navViewBottom?.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem):
            Boolean {
        return item.onNavDestinationSelected(findNavController
            (R.id.nav_host_fragment))
    }
}