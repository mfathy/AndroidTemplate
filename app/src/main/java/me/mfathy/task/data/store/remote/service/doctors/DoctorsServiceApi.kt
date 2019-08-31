package me.mfathy.task.data.store.remote.service.doctors

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Mohammed Fathy
 * dev.mfathy@gmail.com
 *
 * DoctorsServiceApi retrofit end point to access remote server api.
 */
interface DoctorsServiceApi {

    @GET("api/")
    fun getItems(
        @Query("search") keyword: String,
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double,
        @Query("lastKey") lastKey: String?
    ): Single<Any>
}