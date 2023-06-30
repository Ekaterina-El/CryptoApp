package com.example.cryptoapp.di

import com.example.cryptoapp.presentation.CoinDetailFragment
import dagger.Subcomponent

@Subcomponent(modules = [ViewModelModel::class])
interface FragmentComponent {
	fun inject(coinDetailFragment: CoinDetailFragment)

	@Subcomponent.Factory
	interface Factory {
		fun create(): FragmentComponent
	}
}