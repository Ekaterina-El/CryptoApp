package com.example.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {
	private lateinit var binding: ActivityCoinDetailBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityCoinDetailBinding.inflate(layoutInflater)
		setContentView(binding.root)

		if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
			finish()
			return
		}

		if (savedInstanceState == null) parseExtra()

	}

	private fun parseExtra() {
		val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: EMPTY_FROM_SYMBOL
		val fragment = CoinDetailFragment.newInstance(fromSymbol)
		supportFragmentManager.beginTransaction()
			.replace(R.id.coin_detail_container, fragment).commit()
	}

	companion object {
		private const val EMPTY_FROM_SYMBOL = ""
		private const val EXTRA_FROM_SYMBOL = "fSym"

		fun newIntent(context: Context, fromSymbol: String): Intent {
			val intent = Intent(context, CoinDetailActivity::class.java)
			intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
			return intent
		}
	}
}
