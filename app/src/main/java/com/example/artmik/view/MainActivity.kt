package com.example.artmik.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.artmik.databinding.ActivityMainBinding
import com.example.artmik.view.utils.showSnackBar
import com.example.artmik.view.utils.whenCreated
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(CreateMethod.INFLATE)
    private val mainViewModel: MainViewModel by viewModels { mainViewModelFactory }

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mainViewModel.getExceptionsFlow().onEach { message ->
            showSnackBar(binding.root, getString(message), true)
        }.whenCreated(lifecycleScope)
    }

}