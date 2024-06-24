package com.oginnovation.voucherssas.data.api




import retrofit2.Call
import retrofit2.http.*

public interface ApiServices {

    @POST("Login/login")
    fun login2(
        @Body loginBody : LoginBody
    ): Call<LoginResponse>
    @POST("Login/register")
    fun register2(
        @Body createAMemberBody : UserResponse
    ): Call<UserResponse>

    @POST("Login/ValidateLoginAccessCode")
    fun activate2(
        @Body activateModel: ActivateModel
    ): Call<ActivateResponse>

    @POST("Login/SendResetAccessCode")
    fun resend2(
        @Body resendActivationBody: ResendActivationBody
    ): Call<ResendActivationResponse>

    @POST("Login/ResetPassword")
    fun passChange2(
        @Body activateModel: ActivateModel
    ): Call<ActivateResponse>

    @POST("User/getEVChargers")
    fun evCharges22(
        @Body evCharges : EvCharges
    ): Call<ChargerListInfo>

  /* @POST("api/Authenticate/Login")
    fun login2(
        @Body loginBody : LoginBody
    ): Call<LoginResponse>

    @POST("api/Authenticate/CreateMember")
    fun register2(
        @Body createAMemberBody : CreateAMemberBody
    ): Call<RegisterResponse>

    @GET("api/Ads/GetActiveAd")
    fun gallery2(

    ): Call<GalleryResponse>*/
}
