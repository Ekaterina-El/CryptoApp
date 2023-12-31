package com.example.cryptoapp.di

import android.app.Application
import android.content.Context
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.database.CoinInfoDao
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.data.network.ApiService
import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
	@Binds
	@ApplicationScope
	fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

	companion object {
		@Provides
		@ApplicationScope
		fun provideApiService(): ApiService {
			return ApiFactory.apiService
		}

		@Provides
		@ApplicationScope
		fun provideCoinInfoDao(appDatabase: AppDatabase): CoinInfoDao {
			return appDatabase.coinPriceInfoDao()
		}

		@Provides
		fun provideAppDatabase(application: Application): AppDatabase {
			return AppDatabase.getInstance(application)
		}
	}
}