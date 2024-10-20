package com.example.effective_mobile_task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.effective_mobile_task.databinding.ActivityMainBinding
import com.example.effective_mobile_task.di.SubComponents
import com.example.effective_mobile_task.utils.updateBadge
import com.example.home.navigation.NavigationInterface
import com.example.ui.utils.FavoriteEvents
import timber.log.Timber

class MainActivity : AppCompatActivity(), SubComponents, NavigationInterface {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        val navController = host.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        menuBadge()
    }

    override fun navigateToRecommendationFragment() {
        findNavController(R.id.nav_host_fragment).navigate(R.id.recommendationFragment)
    }

    private fun menuBadge() {
        val menuItem = binding.bottomNavigationView.menu.findItem(R.id.favoriteFragment)

        FavoriteEvents.favoriteCount.observe(this) { count ->
            Timber.d("Favorite vacancy count: $count")
            menuItem?.updateBadge(count, this)
        }
    }
}
