package com.example.effective_mobile_task

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.core.listeners.FavoriteVacanciesListener
import com.example.effective_mobile_task.databinding.ActivityMainBinding
import com.example.effective_mobile_task.di.SubComponents
import com.example.home.navigation.NavigationInterface
import com.example.ui.utils.showBadge
import java.util.Locale

class MainActivity : AppCompatActivity(), SubComponents, NavigationInterface,
    FavoriteVacanciesListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        val navController = host.navController

        binding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun attachBaseContext(newBase: Context) {
        val locale = Locale("ru")
        val config = Configuration(newBase.resources.configuration)
        config.setLocale(locale)
        val context = newBase.createConfigurationContext(config)
        super.attachBaseContext(context)
    }

    override fun navigateToRecommendationFragment() {
        findNavController(R.id.nav_host_fragment).navigate(R.id.recommendationFragment)

        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.recommendationFragment -> binding.bottomNavigationView.menu.findItem(R.id.homeFragment).isChecked = true
            }
        }
    }

    override fun onCountPass(count: Int) {
        binding.bottomNavigationView.showBadge(R.id.favoriteFragmentMenu, count)
    }
}
