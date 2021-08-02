package com.niv_angert.anydo.presentation.color

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.niv_angert.anydo.databinding.ActivityColorBinding

class ColorActivity : AppCompatActivity() {

    // UI:
    private lateinit var binding: ActivityColorBinding

    // Lifecycle -----------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityColorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val time = intent?.getLongExtra("time", 0)
        ViewCompat.setTransitionName(binding.marketItemCircleVw1, "marketItemCircleVw$time")
    }
}