package com.markkhakimulin.revolut.presentator.di.modules

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by Mark Khakimulin on 09.09.2019.
 * Email : mark.khakimulin@gmail.com
 */
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)