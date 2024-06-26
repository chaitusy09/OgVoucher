package com.oginnovation.voucherssas.data.di

import android.content.Context
import com.oginnovation.voucherssas.data.api.ApiConfig
import com.oginnovation.voucherssas.data.datastore.RemoteDataSource
import com.oginnovation.voucherssas.data.repository.MainRepository

object Injection {
    fun provideRepository(context: Context): MainRepository {
        val apiServices = ApiConfig.getApiService(context)
        val remoteDataSource = RemoteDataSource(apiServices)
        return MainRepository(remoteDataSource)
    }
}