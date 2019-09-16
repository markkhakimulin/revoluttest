package com.markkhakimulin.revolut.presentator.ui.list

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable


/**
 * Created by Mark Khakimulin on 13.09.2019.
 * Email : mark.khakimulin@gmail.com
 */
class CurrencyViewModel constructor(@Bindable val name: String = "empty",
                                    @Bindable var rate: Double = 0.00,
                                    var base:Boolean = false,
                                    @Bindable var description:String?= "no resource found for $name",
                                    @Bindable var img:Int = 0): BaseObservable()
