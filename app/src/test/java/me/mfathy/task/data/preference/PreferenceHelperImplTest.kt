package me.mfathy.task.data.preference

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import me.mfathy.task.data.models.AccessToken
import me.mfathy.task.factory.DoctorsDataFactory
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 */
@RunWith(RobolectricTestRunner::class)
class PreferenceHelperImplTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mPreferenceHelper: PreferenceHelperImpl

    @Before
    fun setUp() {
        mPreferenceHelper = PreferenceHelperImpl(RuntimeEnvironment.application.applicationContext)
    }

    @Test
    fun testSetAccessTokenSavesData() {
        val accessTokenEntity = DoctorsDataFactory.makeAccessToken()
        mPreferenceHelper.setAccessToken(accessTokenEntity)

        val savedAccessToken = mPreferenceHelper.getAccessToken()

        assertEqualsData(accessTokenEntity, savedAccessToken)
    }

    private fun assertEqualsData(entity: AccessToken, model: AccessToken) {
        assertEquals(entity.accessToken, model.accessToken)
        assertEquals(entity.expiresIn, model.expiresIn)
    }


}