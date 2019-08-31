package me.mfathy.task.data.store.remote.service.doctors

import me.mfathy.task.data.preference.PreferenceHelper
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 *
 * Authentication interceptor to provide access token to all remote api services.
 */
class OAuthHeaderInterceptor @Inject constructor(
    private val preferenceHelper: PreferenceHelper
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val accessToken = preferenceHelper.getAccessToken()

        val original = chain.request()
        val builder = original.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .header("Accept", "application/json")

        val request = builder.build()
        return chain.proceed(request)
    }

}