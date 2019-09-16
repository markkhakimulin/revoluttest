package com.markkhakimulin.revolut.presentator.ui.list

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.markkhakimulin.revolut.data.response.NetResponce
import com.markkhakimulin.revolut.domain.CurrencyRepository
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Mark Khakimulin on 06.09.2019.
 * Email : mark.khakimulin@gmail.com
 */

class CurrencyListViewModel
@Inject constructor(private val currencyRepository: CurrencyRepository) : ViewModel() {

    private var currencyJob: Job? = null

    val rates = MutableLiveData<List<CurrencyViewModel>>().apply {
        value = listOf(CurrencyViewModel(BASE_INIT,1.0,true))
    }
    val error = MutableLiveData<String>()
    val loading =  ObservableInt(View.GONE)
    val rv = ObservableInt(View.GONE)

    companion object {
        private val BASE_INIT: String = "EUR";
    }

    private fun getFirstElement():CurrencyViewModel {
        return rates.value!![0]
    }

    fun getLatest() {

        currencyJob = GlobalScope.launch(Dispatchers.IO, CoroutineStart.DEFAULT, null, {

            delay(100)

            while (isActive) { //looping this call

                val value = currencyRepository.getLatestCurrency(getFirstElement().name)

                when (value) {
                    is NetResponce.Success -> {
                        val list : ArrayList<CurrencyViewModel> =
                            value.data.rates.map { entry ->  CurrencyViewModel(entry.key,(entry.value * getFirstElement().rate),false)} as ArrayList<CurrencyViewModel>
                        list.add(0,getFirstElement())//adding first element as a base

                        rates.postValue(list)//already notifyChange

                        rv.set(View.VISIBLE)
                        loading.set(View.GONE)
                    }
                    is NetResponce.Error -> {
                        error.postValue(value.exception.message)
                        loading.set(View.GONE)
                    }
                }
                delay(1, TimeUnit.SECONDS)
            }

        })
    }


    override fun onCleared() {
        super.onCleared()
        currencyJob?.cancel()
        GlobalScope.coroutineContext.cancelChildren()
    }
}
