package com.oginnovation.voucherssas.data.datastore

import android.util.Log
import com.oginnovation.voucherssas.data.api.ApiServices
import com.oginnovation.voucherssas.token.TokenBody

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiServices: ApiServices) {
    fun getToken(loginBody: TokenBody) = flow {
        emit(apiServices.getToken2(loginBody))
    }.catch {
        Log.d("login", "login: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    /*fun register(createAMemberBody: UserResponse) = flow {
        emit(apiServices.register2(createAMemberBody))
    }.catch {
        Log.d("login", "login: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    fun activate(activateModel: ActivateModel) = flow {
        emit(apiServices.activate2(activateModel))
    }.catch {
        Log.d("login", "login: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    fun resend(resendActivationBody: ResendActivationBody) = flow {
        emit(apiServices.resend2(resendActivationBody))
    }.catch {
        Log.d("login", "login: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    fun passChange(activateModel: ActivateModel) = flow {
        emit(apiServices.passChange2(activateModel))
    }.catch {
        Log.d("login", "login: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)*/
    /*fun login(loginBody: LoginBody) = flow {
        emit(apiServices.login2(loginBody))
    }.catch {
        Log.d("login", "login: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    fun register(createAMemberBody: CreateAMemberBody) = flow {
        emit(apiServices.register2(createAMemberBody))
    }.catch {
        Log.d("login", "login: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)

    fun gallery2() = flow {
        emit(apiServices.gallery2())
    }.catch {
        Log.d("login", "login: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)*/
    /*fun evCharge(evCharges: EvCharges) = flow {
        emit(apiServices.evCharges22(evCharges))
    }.catch {
        Log.d("login", "login: failed = ${it.message}")
    }.flowOn(Dispatchers.IO)*/

}