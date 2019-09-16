package com.markkhakimulin.revolut.presentator.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.markkhakimulin.revolut.presentator.base.ViewModelFactory
import com.markkhakimulin.revolut.presentator.ui.list.CurrencyListViewModel
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.multibindings.IntoMap

/**
 * Created by Mark Khakimulin on 09.09.2019.
 * Email : mark.khakimulin@gmail.com
 */

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyListViewModel::class)
    @Reusable
    abstract fun bindListViewModel(currencyListViewModel: CurrencyListViewModel): ViewModel


    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory
}