package me.mfathy.task.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import me.mfathy.task.TestApplication
import me.mfathy.task.data.repository.Repository
import me.mfathy.task.injection.module.UiModule
import me.mfathy.task.injection.module.ViewModelsModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        TestApplicationModule::class,
        TestDataModule::class,
        ViewModelsModule::class,
        TestRemoteModule::class,
        UiModule::class]
)
interface TestApplicationComponent {

    fun repository(): Repository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestApplicationComponent
    }

    fun inject(application: TestApplication)

}