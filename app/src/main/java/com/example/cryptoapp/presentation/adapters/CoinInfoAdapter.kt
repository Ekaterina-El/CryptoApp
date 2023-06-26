package com.example.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.databinding.ItemCoinInfoBinding
import com.example.cryptoapp.domain.CoinInfo

class CoinInfoAdapter(private val context: Context) : ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffCallback())  {
	var onCoinClickListener: OnCoinClickListener? = null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
		val binding = ItemCoinInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return CoinInfoViewHolder(binding)
	}

	override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
		val coin = getItem(position)
		holder.binding(coin) { onCoinClickListener?.onCoinClick(it) }
	}

	interface OnCoinClickListener {
		fun onCoinClick(coinInfo: CoinInfo)
	}
}