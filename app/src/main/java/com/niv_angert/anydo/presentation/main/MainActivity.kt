package com.niv_angert.anydo.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.niv_angert.anydo.R
import com.niv_angert.anydo.databinding.ActivityMainBinding
import com.niv_angert.anydo.koin.KoinConsts
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {

    // Lazy Inject ViewModel:
    private val viewModel: MainViewModel by viewModel(
        qualifier = named(KoinConsts.MAIN_VIEW_MODEL)
    )

    // UI:
    private lateinit var binding: ActivityMainBinding

    // Lifecycle -----------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    fun init(){

    }

}