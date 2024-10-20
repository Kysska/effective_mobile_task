package com.example.effective_mobile_task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.effective_mobile_task.databinding.ActivityMainBinding
import com.example.effective_mobile_task.di.DiProvider
import com.example.home.di.HomeComponent
import com.example.home.di.HomeComponentProvider
import com.example.home.navigation.NavigationInterface

class MainActivity : AppCompatActivity(), HomeComponentProvider, NavigationInterface {

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

    override fun provideHomeComponent(): HomeComponent {
        return DiProvider.appComponent().homeComponent.create()
    }

    override fun navigateToRecommendationFragment() {
        findNavController(R.id.nav_host_fragment).navigate(R.id.recommendationFragment)
    }
}
