package com.example.cryptoapp.presentation

import android.app.Application
import com.example.cryptoapp.di.DaggerAppComponent

class CoinApplication: Application() {
	val component by lazy {
		DaggerAppComponent.factory().create(this)
	}
}