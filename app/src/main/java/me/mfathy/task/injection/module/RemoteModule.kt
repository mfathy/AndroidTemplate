package me.mfathy.task.injection.module

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import me.mfathy.task.BuildConfig
import me.mfathy.task.data.preference.PreferenceHelper
import me.mfathy.task.data.store.remote.RemoteDataStore
import me.mfathy.task.data.store.remote.RemoteStore
import me.mfathy.task.data.store.remote.service.ServiceFactory
import me.mfathy.task.data.store.remote.service.auth.AuthServiceApi
import me.mfathy.task.data.store.remote.service.auth.LoginHeaderInterceptor
import me.mfathy.task.data.store.remote.service.doctors.DoctorsServiceApi
import me.mfathy.task.data.store.remote.service.doctors.OAuthHeaderInterceptor
import me.mfathy.task.data.store.remote.utils.NetworkConnection
import me.mfathy.task.data.store.remote.utils.NetworkConnectionImpl

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 *
 * Dagger module to provide remote dependencies.
 */
@Module
abstract class RemoteModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun providesNetworkConnection(context: Context): NetworkConnection =
            NetworkConnectionImpl(context)

        @Provides
        @JvmStatic
        fun provideDoctorsServiceApi(interceptor: OAuthHeaderInterceptor): DoctorsServiceApi {
            return ServiceFactory.makeRemoteService(BuildConfig.DEBUG, interceptor)
        }

        @Provides
        @JvmStatic
        fun provideAuthServiceApi(interceptor: LoginHeaderInterceptor): AuthServiceApi {
            return ServiceFactory.makeAuthService(BuildConfig.DEBUG, interceptor)
        }

        @Provides
        @JvmStatic
        fun provideOAuthHeaderInterceptor(preferenceHelper: PreferenceHelper): OAuthHeaderInterceptor {
            return OAuthHeaderInterceptor(preferenceHelper)
        }

        @Provides
        @JvmStatic
        fun provideLoginHeaderInterceptor(): LoginHeaderInterceptor {
            return LoginHeaderInterceptor()
        }
    }

    @Binds
    abstract fun bindRemoteStore(remote: RemoteDataStore): RemoteStore


}