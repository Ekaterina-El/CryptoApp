package com.example.cryptoapp.data.workers

import android.app.Application
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.cryptoapp.data.database.CoinInfoDao
import com.example.cryptoapp.data.mapper.CoinMapper
import com.example.cryptoapp.data.network.ApiService
import kotlinx.coroutines.delay

class RefreshDataWorker(
	application: Context, workerParams: WorkerParameters, private val coinInfoDao: CoinInfoDao,
	private val apiService: ApiService,
	private val mapper: CoinMapper
) : CoroutineWorker(
	application, workerParams
) {

	override suspend fun doWork(): Result {
		while (true) {
			try {
				val topCoins = apiService.getTopCoinsInfo(limit = 50)
				val fSyms = mapper.mapNamesListToString(topCoins)
				val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
				val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
				val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
				coinInfoDao.insertPriceList(dbModelList)
			} catch (_: Exception) {
			}
			delay(10000)
		}
	}

	companion object {
		const val NAME = "refreshDataWorker"

		fun makeRequest(): OneTimeWorkRequest {
			return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
		}
	}
}