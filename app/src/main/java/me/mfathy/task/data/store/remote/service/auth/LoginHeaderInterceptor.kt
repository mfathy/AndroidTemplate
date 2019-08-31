package me.mfathy.task.data.store.remote.service.auth

import me.mfathy.task.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 *
 * Authentication interceptor to provide access token to all remote api services.
 */
class LoginHeaderInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        val builder = original.newBuilder()
            .header("Authorization", BuildConfig.AUTHORIZATION)
            .header("Accept", "application/json")
            .header("Content-Type", "application/x-www-form-urlencoded")

        val request = builder.build()
        return chain.proceed(request)
    }

}