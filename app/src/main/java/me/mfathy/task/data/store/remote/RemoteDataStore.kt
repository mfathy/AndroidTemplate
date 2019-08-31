package me.mfathy.task.data.store.remote

import io.reactivex.Single
import me.mfathy.task.data.exceptions.NetworkException
import me.mfathy.task.data.store.remote.service.auth.AuthServiceApi
import me.mfathy.task.data.store.remote.utils.NetworkConnection
import javax.inject.Inject


/**
 * Remote Data Store implementation.
 */
open class RemoteDataStore @Inject constructor(
    private val networkConnection: NetworkConnection,
    private val authServiceApi: AuthServiceApi
) : RemoteStore {
    override fun login(username: String, password: String): Single<Any> {
        return if (!networkConnection.hasInternetConnection()) Single.error(NetworkException())
        else authServiceApi.login(KEY_GRANT_TYPE, username, password)
    }



    companion object {
        const val KEY_GRANT_TYPE = "password"
    }
}
