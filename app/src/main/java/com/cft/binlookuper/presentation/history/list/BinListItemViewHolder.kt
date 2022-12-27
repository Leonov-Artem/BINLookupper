package com.cft.binlookuper.presentation.history.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cft.binlookuper.databinding.BinListItemBinding

class BinListItemViewHolder(
    private val binding: BinListItemBinding,
    private val callbacks: SearchHistoryFragment.Callbacks,
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var bin: String

    init {
        binding.cardView.setOnClickListener(this)
    }

    fun bind(bin: String) {
        this.bin = bin
        binding.binTextView.text = bin
    }

    override fun onClick(p0: View?) {
        callbacks.onSearchHistoryListItemClicked(bin)
    }
}