package com.example.cryptoapp.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.databinding.ItemCoinInfoBinding
import com.example.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoViewHolder(private val binding: ItemCoinInfoBinding) :
	RecyclerView.ViewHolder(binding.root) {
	fun binding(coinInfo: CoinInfo, clickListener: (CoinInfo) -> Unit) {
		binding.item = coinInfo
		Picasso.get().load(coinInfo.imageUrl).into(binding.ivLogoCoin)
		binding.root.setOnClickListener {
			clickListener(coinInfo)
		}
	}
}