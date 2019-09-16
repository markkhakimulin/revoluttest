package com.markkhakimulin.revolut.presentator.di.components

import android.app.Application
import com.markkhakimulin.revolut.presentator.base.AndroidApplication
import com.markkhakimulin.revolut.presentator.di.modules.ActivityBindingModule
import com.markkhakimulin.revolut.presentator.di.modules.ApiServiceModule
import com.markkhakimulin.revolut.presentator.di.modules.ContextModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

/**
 * Created by Mark Khakimulin on 04.09.2019.
 * Email : mark.khakimulin@gmail.com
 */

@Singleton
@Component(modules = [
    (ApiServiceModule::class),
    (ContextModule::class),
    (AndroidSupportInjectionModule::class),
    (ActivityBindingModule::class)])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: AndroidApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build() : ApplicationComponent
    }
}