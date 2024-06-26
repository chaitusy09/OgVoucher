package com.kuwait.pravastelugu.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel





import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import androidx.lifecycle.viewModelScope
import com.oginnovation.voucherssas.data.repository.MainRepository
import com.oginnovation.voucherssas.token.TokenBody
import com.oginnovation.voucherssas.token.TokenResponse
import com.oginnovation.voucherssas.utility.Event
import com.oginnovation.voucherssas.utility.Resource
import com.oginnovation.voucherssas.utility.isNetworkConnected


class CommonViewModel(private val repository: MainRepository) : ViewModel() {


    /*Login Related Api and Response start*/
    private val _generateTokenData = MutableLiveData<Event<Resource<TokenResponse>>>()
    val generateTokendata: LiveData<Event<Resource<TokenResponse>>> = _generateTokenData
    fun generateToken(tokenBody: TokenBody, context: Context) = viewModelScope.launch {
        _generateTokenData.postValue(Event(Resource.Loading()))
        try {
            if (isNetworkConnected(context)) {
                repository.generateToken(tokenBody).collect {
                    it.enqueue(object : Callback<TokenResponse> {
                        override fun onResponse(
                            call: Call<TokenResponse>,
                            response: Response<TokenResponse>
                        ) {
                            if (response.isSuccessful) {
                                val status = response.code() ?: -1
                                if (status == 200) {
                                    if (response.body() != null) {
                                        try{
                                            val tokenResponse:TokenResponse= response.body()!!;
                                            if(tokenResponse!=null&&tokenResponse.statusCode!=null&&tokenResponse.statusCode.equals(200)){
                                                _generateTokenData.postValue(Event(Resource.Success(response.body()!!)))
                                            }else{
                                                if(tokenResponse!=null&&tokenResponse.message!=null) {
                                                    _generateTokenData.postValue(
                                                        Event(
                                                            Resource.Error(
                                                                tokenResponse.message.toString()
                                                            )
                                                        )
                                                    )
                                                }else{
                                                    _generateTokenData.postValue(
                                                        Event(
                                                            Resource.Error(
                                                                "Some Unknown error has occured!!"
                                                            )
                                                        )
                                                    )
                                                }
                                            }


                                            /*val loginResponse:LoginResponse= response.body()!!;
                                            if(loginResponse.Message!=null&&!loginResponse.Message.isEmpty()){
                                                _loginData.postValue(Event(Resource.Error(loginResponse.Message.toString())))
                                            }else{
                                                if(loginResponse.ResultInfo!=null&&loginResponse.ResultInfo.IsSuccessful!=null && loginResponse.ResultInfo.IsSuccessful) {
                                                _loginData.postValue(Event(Resource.Success(response.body()!!)))
                                                }else if(loginResponse.ResultInfo!=null && loginResponse.ResultInfo.Message!=null && !loginResponse.ResultInfo.Message.isEmpty() ){
                                                    _registerData.postValue(Event(Resource.Error(loginResponse.ResultInfo.Message.toString())))
                                                }
                                            }*/
                                        }catch (e:Exception){
                                            _generateTokenData.postValue(Event(Resource.Error(" Unknown Error")))
                                        }

                                    }else{
                                        _generateTokenData.postValue(Event(Resource.Error(" Unknown Error")))
                                    }
                                } else {
                                    _generateTokenData.postValue(Event(Resource.Error(" Unknown Error")))
                                }
                            }else {

                                _generateTokenData.postValue(Event(Resource.Error(" Unknown Error")))
                            }
                        }
                        override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                            _generateTokenData.postValue(Event(Resource.Error(" Unknown Error")))
                        }
                    })
                }
            } else {
                _generateTokenData.postValue(Event(Resource.Error("No Internet Connection")))
                //toast(context, "No Internet Connection.!")
            }
        } catch (e: HttpException) {
            when (e) {
                is IOException -> {
                    _generateTokenData.postValue(Event(Resource.Error(e.message!!)))
                }
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _generateTokenData.postValue(Event(Resource.Error(t.message!!)))
                }
            }
        }
    }
}
