package me.mfathy.task.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.mfathy.task.features.main.MainActivity
import me.mfathy.task.injection.scope.ViewScope

/**
 * Dagger module to provide UI and activities dependencies.
 */
@Module
abstract class UiModule {
    @ViewScope
    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
}