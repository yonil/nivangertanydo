package com.niv_angert.anydo.presentation.main

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.niv_angert.anydo.R
import com.niv_angert.anydo.databinding.ActivityMainBinding
import com.niv_angert.anydo.koin.KoinConsts
import com.niv_angert.anydo.presentation.color.ColorActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {

    // Lazy Inject ViewModel:
    private val viewModel: MainViewModel by viewModel(
        qualifier = named(KoinConsts.MAIN_VIEW_MODEL)
    )

    // UI:
    private lateinit var binding: ActivityMainBinding

    // Params:
    private var adapter: BagsAdapter? = null

    // Lifecycle -----------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }


    private fun init() {

        initList()

        observeBagsFeed()

        handleConnectionToggle()
    }

    // Feed ----------------------------------------------------------------------------------------

    private fun initList() {

        binding.run {

            adapter = BagsAdapter { marketItemCircleVw,time ->
                handleBagClick(marketItemCircleVw,time)
            }

            val layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

            mainRecyclerVw.layoutManager = layoutManager

            mainRecyclerVw.adapter = adapter
        }
    }

    private fun observeBagsFeed() {
        viewModel.bagsLiveData.observe(this) { feed ->

            when (feed) {
                null -> {
                    adapter?.submitList(listOf())
                    adapter?.notifyDataSetChanged()
                }
                else -> {
                    // Handle feed:

                    adapter?.submitList(feed)
                    adapter?.notifyDataSetChanged()
                }
            }
        }
    }

    private fun handleBagClick(marketItemCircleVw: View, time: Long) {

        // Get connection state:
        val isConnected = viewModel.connectedLiveData.value ?: true

        // Prevent clicks when connected:
        if (isConnected) {
            return
        }

        // Start Activity:
        val intent = Intent(this, ColorActivity::class.java)
        val options = ActivityOptions
            .makeSceneTransitionAnimation(this, marketItemCircleVw, "marketItemCircleVw$time")

        intent.putExtra("time", time)

        startActivity(intent, options.toBundle())
    }

    // Connection ----------------------------------------------------------------------------------

    private fun handleConnectionToggle() {

        binding.toggleConnectionIb.setOnClickListener {

            // Get connection state:
            val isConnected = viewModel.connectedLiveData.value ?: true

            // Update icon:
            val icon = if (isConnected) {
                Toast.makeText(this, R.string.ic_disconnected, Toast.LENGTH_SHORT).show()
                ResourcesCompat.getDrawable(resources, R.drawable.ic_connect, null)
            } else {
                Toast.makeText(this, R.string.ic_connected, Toast.LENGTH_SHORT).show()
                ResourcesCompat.getDrawable(resources, R.drawable.ic_disconnect, null)
            }
            binding.toggleConnectionIb.setImageDrawable(icon)

            // Update Vm:
            viewModel.onConnectionChanged(!isConnected)
        }
    }
}