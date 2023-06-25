package com.example.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.databinding.ActivityCoinPrceListBinding
import com.example.cryptoapp.domain.CoinInfo
import com.example.cryptoapp.presentation.adapters.CoinInfoAdapter

class CoinPriceListActivity : AppCompatActivity() {
	private lateinit var binding: ActivityCoinPrceListBinding
	private lateinit var viewModel: CoinViewModel
	private val adapter by lazy {
		val adapter = CoinInfoAdapter(this)
		adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
			override fun onCoinClick(coinInfo: CoinInfo) {
				val intent =
					CoinDetailActivity.newIntent(this@CoinPriceListActivity, coinInfo.fromSymbol)
				startActivity(intent)
			}
		}
		return@lazy adapter
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityCoinPrceListBinding.inflate(layoutInflater)
		binding.apply {
			adapter = this@CoinPriceListActivity.adapter
			lifecycleOwner
		}

		setContentView(binding.root)

		viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
		viewModel.priceList.observe(this) {
			adapter.submitList(it)
		}
	}
}
