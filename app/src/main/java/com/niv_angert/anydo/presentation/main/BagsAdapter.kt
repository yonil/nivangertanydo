package com.niv_angert.anydo.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.niv_angert.anydo.R
import com.niv_angert.anydo.data.entities.Bag

/**
 *Created by Niv Angert on 02/08/2021
 **/
class BagsAdapter(private val clickCallback: (marketItemCircleVw: View) -> Unit) :
    ListAdapter<Bag, BagViewHolder>(BagsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagViewHolder {
        return BagViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_bag,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BagViewHolder, position: Int) {
        holder.bind(getItem(position), clickCallback)
    }
}

class BagsDiffUtil : DiffUtil.ItemCallback<Bag>() {

    override fun areItemsTheSame(oldItem: Bag, newItem: Bag): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Bag, newItem: Bag): Boolean {
        return oldItem == newItem
    }
}