package me.mfathy.task.injection.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import me.mfathy.task.App


/**
 * Dagger application module to provide app mContext.
 */
@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindContext(application: App): Context

    @Binds
    abstract fun bindApplication(application: App): Application

}
