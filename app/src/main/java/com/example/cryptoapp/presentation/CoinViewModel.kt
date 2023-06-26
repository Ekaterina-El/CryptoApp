package com.example.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.cryptoapp.domain.CoinInfo
import com.example.cryptoapp.domain.GetCoinInfoListUseCase
import com.example.cryptoapp.domain.GetCoinInfoUseCase
import com.example.cryptoapp.domain.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {
	private val rep = CoinRepositoryImpl(application)
	private val getCoinInfoListUseCase = GetCoinInfoListUseCase(rep)
	private val getCoinInfoUseCase = GetCoinInfoUseCase(rep)
	private val loadDataUseCase = LoadDataUseCase(rep)

	val priceList = getCoinInfoListUseCase()

	fun getDetailInfo(fSym: String): LiveData<CoinInfo> {
		return getCoinInfoUseCase(fSym)
	}

	init {
		loadData()
	}

	private fun loadData() {
		loadDataUseCase()
	}
}