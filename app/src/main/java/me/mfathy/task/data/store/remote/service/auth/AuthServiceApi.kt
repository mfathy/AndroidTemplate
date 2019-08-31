package me.mfathy.task.data.store.remote.service.auth

import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Mohammed Fathy
 * dev.mfathy@gmail.com
 *
 * DoctorsServiceApi retrofit end point to access remote server api.
 */
interface AuthServiceApi {

    @FormUrlEncoded
    @POST("oauth/token")
    fun login(
        @Query("grant_type") grantType: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Single<Any>
}