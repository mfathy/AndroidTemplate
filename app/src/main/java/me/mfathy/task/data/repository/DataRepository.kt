package me.mfathy.task.data.repository

import io.reactivex.Completable
import me.mfathy.task.data.preference.PreferenceHelper
import me.mfathy.task.data.store.remote.RemoteStore
import javax.inject.Inject

/**
 * DataRepository repository implementation
 */
class DataRepository @Inject constructor(
    private val remoteStore: RemoteStore,
    private val preferenceHelper: PreferenceHelper
) : Repository {
    override fun login(username: String, password: String): Completable {
        return remoteStore.login(username, password)
            .doOnSuccess {
                preferenceHelper.setAccessToken(it)
            }.ignoreElement()

    }
}