package me.mfathy.task.data.preference


/**
 * Created by Mohammed Fathy
 * dev.mfathy@gmail.com
 *
 * PreferenceHelper Contract to read/write access token.
 */
interface PreferenceHelper {
    fun getAccessToken(): Any
    fun setAccessToken(accessToken: Any)
}