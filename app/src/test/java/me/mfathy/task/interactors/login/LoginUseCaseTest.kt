package me.mfathy.task.interactors.login

import io.reactivex.Completable
import me.mfathy.task.data.repository.Repository
import me.mfathy.task.factory.DataFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 */
@RunWith(MockitoJUnitRunner::class)
class LoginUseCaseTest {

    private lateinit var loginUseCase: LoginUseCase

    @Mock
    lateinit var mockRepository: Repository

    val params = LoginUseCase.Params(DataFactory.randomString(), DataFactory.randomString())


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        loginUseCase = LoginUseCase(mockRepository)
    }

    @Test
    fun testLoginCompletes() {

        stubRepositoryLogin()

        loginUseCase.buildUseCaseCompletable(params)
            .test()
            .assertComplete()
    }

    @Test
    fun testLoginCallRepo() {

        stubRepositoryLogin()

        loginUseCase.buildUseCaseCompletable(params)
            .test()

        Mockito.verify(mockRepository).login(anyString(), anyString())
    }

    private fun stubRepositoryLogin() {
        Mockito.`when`(mockRepository.login(anyString(), anyString()))
            .thenReturn(Completable.complete())
    }
}