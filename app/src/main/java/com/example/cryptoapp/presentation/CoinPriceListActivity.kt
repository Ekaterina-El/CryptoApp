package com.example.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityCoinPrceListBinding
import com.example.cryptoapp.domain.CoinInfo
import com.example.cryptoapp.presentation.adapters.CoinInfoAdapter
import javax.inject.Inject

class CoinPriceListActivity : AppCompatActivity() {
	@Inject
	lateinit var viewModelFactory: ViewModelFactory
	private val viewModel: CoinViewModel by lazy {
		ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
	}
	private val component by lazy {
		(application as CoinApplication).component.activityComponentFactory().create()
	}

	private lateinit var binding: ActivityCoinPrceListBinding
	private val adapter by lazy {
		val adapter = CoinInfoAdapter(this)
		adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
			override fun onCoinClick(coinInfo: CoinInfo) {
				val isLand = binding.coinDetailContainer2 != null
				if (isLand) {
					val fragment = CoinDetailFragment.newInstance(coinInfo.fromSymbol)
					supportFragmentManager.popBackStack()
					supportFragmentManager.beginTransaction().replace(R.id.coin_detail_container_2, fragment)
						.addToBackStack(null).commit()
				} else {
					val intent = CoinDetailActivity.newIntent(this@CoinPriceListActivity, coinInfo.fromSymbol)
					startActivity(intent)
				}
			}
		}
		return@lazy adapter
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityCoinPrceListBinding.inflate(layoutInflater)
		binding.rvCoinPriceList.itemAnimator = null
		binding.apply {
			adapter = this@CoinPriceListActivity.adapter
			lifecycleOwner
		}

		setContentView(binding.root)
		component.inject(this)

		viewModel.priceList.observe(this) {
			adapter.submitList(it)
		}
	}
}
