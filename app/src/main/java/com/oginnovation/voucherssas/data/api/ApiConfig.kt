package com.oginnovation.voucherssas.data.api


import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.google.gson.GsonBuilder

import okhttp3.Interceptor
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.provider.Settings;
import com.oginnovation.voucherssas.utility.GlobalState


class ApiConfig constructor(mContext: Context) {


    companion object {
       // val url="http://185.103.120.94:9090/LadzAPI/api/v1/"

        val url="https://demovoucherapi.ogstack.com/api/v1/"


        var gs : GlobalState = GlobalState().GetGlobalState()!!

      /* private val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        }*/ //else {*/
       //private val loggingInterceptor =ContentTypeInterceptor(contexts)
        //}

        fun getSomething(context: Context): Retrofit {
            // val interceHeader =ContentTypeInterceptor(context)
             val client = OkHttpClient.Builder()
                //.addInterceptor(interceHeader)
                 .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
            val gson = GsonBuilder()
                .setLenient()
                .create()
             val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
            return retrofit
        }
        fun getApiService(context: Context): ApiServices {
           return getSomething(context).create(ApiServices::class.java)
        }
    }
    class ContentTypeInterceptor(mContext: Context) : Interceptor {
        val mContext=mContext
        var vers = "" + Build.VERSION.RELEASE
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
           val request = chain.request()
           HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val  newRequest = request.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization","Bearer "+gs.retrieveSharedPreferences(mContext,gs.TOKEN,""))
                .addHeader("Name", Build.MANUFACTURER)
                .addHeader("Model", Build.MODEL)
                .addHeader("os", "android")
                .addHeader("Version", vers)
                .addHeader("AppVersion", versionName(mContext))
                .addHeader("UniqueId", getDeviceId(mContext))
               //.addHeader("Category", header_namecategory)
                .build()

            return chain.proceed(newRequest)
        }

        fun versionName(mContext: Context): String {
            return try {
                val pInfo =
                    mContext.packageManager.getPackageInfo(mContext.packageName, 0)
                pInfo.versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                ""
            }
        }
        private fun getDeviceId(context: Context): String {
            return try {
                Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
        }
    }
}

