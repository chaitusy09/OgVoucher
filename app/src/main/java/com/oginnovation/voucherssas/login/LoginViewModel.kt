package com.oginnovation.voucherssas.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _url = MutableLiveData<String>()
    val url: LiveData<String> = _url

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        _url.value = "https://oneinsso.ogstack.com/auth/Authenticate?ReturnUrl=%2Fauth%2Fconnect%2Fauthorize%3Fclient_id%3D2018f10d-5814-4851-ae0e-12f66aad1af4%26redirect_uri%3Dhttp%253A%252F%252Flocalhost%253A4200%26scope%3Dopenid%2520email%2520phone%26response_type%3Dcode%26response_mode%3Dquery%26state%3Dyjc01d9xd0k%26nonce%3Dw8krluvgxdy"
        _isLoading.value = true
    }

    fun onPageStarted() {
        _isLoading.value = true

    }

    fun onPageFinished() {
        _isLoading.value = true
    }
}
