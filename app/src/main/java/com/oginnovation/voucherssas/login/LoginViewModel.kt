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
        _url.value = "https://oneinsso.ogstack.com/auth/Authenticate?ReturnUrl=%2Fauth%2Fconnect%2Fauthorize%3Fresponse_type%3Dcode%26response_mode%3Dquery%26client_id%3D2018f10d-5814-4851-ae0e-12f66aad1af4%26redirect_uri%3Dhttps%253A%252F%252Fdemovoucher.ogstack.com%26state%3DQ87K2y0EVf%26nonce%3DnfaV0gickh%26scope%3Dopenid%2520profile%2520email%2520phone"
        _isLoading.value = true
    }

    fun onPageStarted() {
        _isLoading.value = true

    }

    fun onPageFinished() {
        _isLoading.value = true
    }
}
