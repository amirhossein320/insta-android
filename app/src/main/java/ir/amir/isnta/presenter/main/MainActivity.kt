package ir.amir.isnta.presenter.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ir.amir.isnta.databinding.ActivityMainBinding
import ir.amir.isnta.util.setLocalApp
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.intent.send(MainIntent.GetLocal)
            viewModel.intent.send(MainIntent.SetContentView)
        }
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        handleState()
    }

    fun handleState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { mainState ->
                when (mainState) {
                    is MainState.ChangeLocal -> setLocalApp(mainState.languageId)
                    is MainState.SetContentView -> {
                        runOnUiThread {
                            binding = ActivityMainBinding.inflate(layoutInflater)
                            setContentView(binding.root)
                        }
                    }
                }
            }
        }
    }

    private fun setupNavHost() {

        val navHost = binding.mainContainer as NavHostFragment

    }
}