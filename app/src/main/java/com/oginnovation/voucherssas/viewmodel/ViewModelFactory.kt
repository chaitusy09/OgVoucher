package com.kuwait.pravastelugu.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oginnovation.voucherssas.data.di.Injection
import com.oginnovation.voucherssas.data.repository.MainRepository


@JvmSuppressWildcards
class ViewModelFactory(private val mainRepository: MainRepository) : ViewModelProvider.Factory {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository(context)).also {
                instance = it
            }
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommonViewModel::class.java)) {
            return CommonViewModel(mainRepository) as T
        }
        if (modelClass.isAssignableFrom(CommonViewModel::class.java)) {
            return CommonViewModel(mainRepository) as T
        }
        throw IllegalArgumentException("unknown ViewModel class: ${modelClass.name}")
    }
}