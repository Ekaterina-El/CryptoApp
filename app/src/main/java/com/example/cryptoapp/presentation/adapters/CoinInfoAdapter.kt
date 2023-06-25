package com.example.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.databinding.ItemCoinInfoBinding
import com.example.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) :
	RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

	var coinInfoList: List<CoinInfo> = listOf()
		set(value) {
			field = value
			notifyDataSetChanged()
		}

	var onCoinClickListener: OnCoinClickListener? = null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
		val binding = ItemCoinInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return CoinInfoViewHolder(binding)
	}

	override fun getItemCount() = coinInfoList.size

	override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
		val coin = coinInfoList[position]
		holder.binding(coin) {
			onCoinClickListener?.onCoinClick(it)
		}
	}

	inner class CoinInfoViewHolder(private val binding: ItemCoinInfoBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun binding(coinInfo: CoinInfo, clickListener: (CoinInfo) -> Unit) {
			binding.item = coinInfo
			Picasso.get().load(coinInfo.imageUrl).into(binding.ivLogoCoin)
			binding.root.setOnClickListener {
				clickListener(coinInfo)
			}
		}
	}

	interface OnCoinClickListener {
		fun onCoinClick(coinInfo: CoinInfo)
	}
}