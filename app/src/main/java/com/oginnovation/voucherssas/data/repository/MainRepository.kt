package com.oginnovation.voucherssas.data.repository

import com.oginnovation.voucherssas.data.datastore.RemoteDataSource
import com.oginnovation.voucherssas.token.TokenBody


class MainRepository(private val remoteDataSource: RemoteDataSource){
    fun generateToken(tokenBody: TokenBody) = remoteDataSource.getToken(tokenBody)

}
