package me.mfathy.task.data.store.remote

import io.reactivex.Single

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 *
 * Data store contract for remote.
 */
interface RemoteStore {
    fun login(username: String, password: String): Single<Any>
}