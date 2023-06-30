package com.example.cryptoapp.di

import com.example.cryptoapp.presentation.CoinPriceListActivity
import dagger.Subcomponent

@Subcomponent(modules = [ViewModelModel::class])
interface ActivityComponent {
	fun inject(coinPriceListActivity: CoinPriceListActivity)

	@Subcomponent.Factory
	interface Factory {
		fun create(): ActivityComponent
	}
}