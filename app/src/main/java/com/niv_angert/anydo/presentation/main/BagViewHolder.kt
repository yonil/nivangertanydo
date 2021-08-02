package com.niv_angert.anydo.presentation.main

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.niv_angert.anydo.data.entities.Bag
import com.niv_angert.anydo.databinding.ViewHolderBagBinding
import com.niv_angert.anydo.presentation.color.ColorActivity


/**
 *Created by Niv Angert on 02/08/2021
 **/
class BagViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    // Params:
    private val binding = ViewHolderBagBinding.bind(itemView)

    // Initialization ------------------------------------------------------------------------------

    fun bind(item: Bag, clickCallback: (marketItemCircleVw: View) -> Unit) {

        binding.run {

            // Color:
            val gradientDrawable = marketItemCircleVw.background as GradientDrawable
            val color = Color.parseColor(item.bagColor)
            gradientDrawable.setColor(color)

            // Name:
            marketItemNameTxtVw.text = item.name

            // Weight:
            marketItemWeightTxtVw.text = item.weight
        }

        binding.marketItemLayout.setOnClickListener {
            clickCallback(binding.marketItemCircleVw)
        }
    }
}