package me.mfathy.task.interactors.login

import io.reactivex.Completable
import me.mfathy.task.data.repository.Repository
import me.mfathy.task.interactors.base.CompletableUseCase
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: Repository
) : CompletableUseCase<LoginUseCase.Params>() {
    override fun buildUseCaseCompletable(params: Params): Completable {
        return repository.login(username = params.username, password = params.password)
    }

    data class Params(val username: String, val password: String)
}