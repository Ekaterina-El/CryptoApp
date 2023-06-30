package com.example.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoapp.data.workers.CoinWorkerFactory
import com.example.cryptoapp.di.DaggerAppComponent
import javax.inject.Inject

class CoinApplication : Application(), Configuration.Provider {
	@Inject
	lateinit var refreshDataWorkerFactory: CoinWorkerFactory

	val component by lazy {
		DaggerAppComponent.factory().create(this)
	}

	override fun onCreate() {
		super.onCreate()
		component.inject(this)
	}

	override fun getWorkManagerConfiguration(): Configuration {
		return Configuration.Builder().setWorkerFactory(refreshDataWorkerFactory).build()
	}
}