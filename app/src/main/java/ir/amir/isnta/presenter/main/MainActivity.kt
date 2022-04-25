package ir.amir.isnta.presenter.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ir.amir.isnta.R
import ir.amir.isnta.databinding.ActivityMainBinding
import ir.amir.isnta.util.gone
import ir.amir.isnta.util.setLocalApp
import ir.amir.isnta.util.visible
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setEvent(MainEvent.GetLocal)
        handleState()
    }

    override fun onSupportNavigateUp(): Boolean {
        return controller.navigateUp()
    }

    private fun handleState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { mainState ->
                when (mainState) {
                    is MainState.IDLE -> {
                    }
                    is MainState.ChangeLocal -> setLocalApp(mainState.languageId)
                    is MainState.SetContentView -> {
                        binding = ActivityMainBinding.inflate(layoutInflater)
                        setContentView(binding.root)
                        setupNavHost()
                    }
                }
            }
        }
    }


    private fun setupNavHost() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        controller = navHost.navController
        controller.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.loginFragment, R.id.forgetPasswordFragment,R.id.signupFragment
                -> binding.bottomNavigation.gone()
                else -> {
                    binding.bottomNavigation.visible()
                }
            }
        }
    }
}