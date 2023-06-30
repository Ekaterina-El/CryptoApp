package com.example.cryptoapp.presentation

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.cryptoapp.domain.CoinInfo
import com.example.cryptoapp.domain.GetCoinInfoListUseCase
import com.example.cryptoapp.domain.GetCoinInfoUseCase
import com.example.cryptoapp.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
	val getCoinInfoListUseCase: GetCoinInfoListUseCase,
	private val getCoinInfoUseCase: GetCoinInfoUseCase,
	private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

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