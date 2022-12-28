package com.cft.binlookupper.presentation.history.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cft.binlookupper.databinding.BinListItemBinding

class BinListAdapter(
    private val binList: List<String>,
    private val callbacks: SearchHistoryFragment.Callbacks,
) : RecyclerView.Adapter<BinListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinListItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BinListItemBinding.inflate(layoutInflater, parent, false)
        return BinListItemViewHolder(binding, callbacks)
    }

    override fun onBindViewHolder(holder: BinListItemViewHolder, position: Int) {
        val bin = binList[position]
        holder.bind(bin)
    }

    override fun getItemCount() = binList.size
}