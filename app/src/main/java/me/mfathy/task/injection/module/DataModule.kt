package me.mfathy.task.injection.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import me.mfathy.task.data.preference.PreferenceHelper
import me.mfathy.task.data.preference.PreferenceHelperImpl
import me.mfathy.task.data.repository.DataRepository
import me.mfathy.task.data.repository.Repository

/**
 * Dagger module to provide data repository dependencies.
 */
@Module
abstract class DataModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun providesPreferenceHelper(application: Application): PreferenceHelper {
            return PreferenceHelperImpl(application)
        }

    }

    @Binds
    abstract fun bindDataRepository(dataRepository: DataRepository): Repository
}