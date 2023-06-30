package com.example.cryptoapp.di

import android.app.Application
import com.example.cryptoapp.presentation.CoinApplication
import com.example.cryptoapp.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class])
@ApplicationScope
interface AppComponent {
	fun activityComponentFactory(): ActivityComponent.Factory
	fun fragmentComponentFactory(): FragmentComponent.Factory
  fun inject(coinApplication: CoinApplication)

	@Component.Factory
	interface Factory {
		fun create(
			@BindsInstance application: Application
		): AppComponent
	}
}