package me.mfathy.task.data.repository

import io.reactivex.Completable

/**
 * Data repository contract.
 */
interface Repository {
    fun login(username: String, password: String): Completable
}
