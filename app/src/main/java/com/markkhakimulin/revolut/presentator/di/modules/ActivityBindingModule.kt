package com.markkhakimulin.revolut.presentator.di.modules

import com.markkhakimulin.revolut.presentator.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Mark Khakimulin on 09.09.2019.
 * Email : mark.khakimulin@gmail.com
 */
@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [FragmentsBindingModule::class])
    abstract fun bindMainActivity(): MainActivity

}