package com.markkhakimulin.revolut.presentator.ui.list

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.markkhakimulin.revolut.R
import com.markkhakimulin.revolut.data.extension.empty
import com.markkhakimulin.revolut.databinding.CurrencyItemBinding
import java.util.*
import java.util.Collections.swap


/**
 * Created by Mark Khakimulin on 09.09.2019.
 * Email : mark.khakimulin@gmail.com
 */
class CurrencyAdapter(

    private val viewModel: CurrencyListViewModel) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {
    private var recyclerView : RecyclerView? = null

    companion object {
        //static ids
        val currencyIds :List<String> = listOf("EUR","AUD","BGN","BRL","CAD","CHF","CNY","CZK",
            "DKK","GBP","HKD","HRK","HUF","IDR","ILS","INR","ISK","JPY","KRW","MXN","MYR","NOK",
            "NZD","PHP","PLN","RON","RUB","SEK","SGD","THB","TRY","USD","ZAR")

    }

    init {
        setHasStableIds(true)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)

        this.recyclerView = null
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {

        val binding:CurrencyItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.currency_item, parent,false)

        return CurrencyViewHolder(binding)
    }

    override fun getItemId(position: Int): Long {
        return currencyIds.indexOf((
            viewModel.rates.value?.let {
                it[position].name
            })
        ).toLong()//return index of name
    }

    override fun getItemCount() = viewModel.rates.value?.size ?: 0


    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {

        viewModel.rates.value?.let {
            it[position].description = getStringByName(holder.itemView.context,it[position].name)
            it[position].img = getDrawableByName(holder.itemView.context,it[position].name.toLowerCase(Locale.ROOT)+"_img")
            holder.setViewData(it[position])
        }

    }

    fun scrollTo(position: Int) {
        viewModel.rates.value?.let {
            it[position].base = true
            swap(viewModel.rates.value, position, 0)
            notifyItemMoved(position, 0)
            recyclerView?.scrollToPosition(0)
        }
    }

    inner class CurrencyViewHolder constructor(val binding: CurrencyItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                scrollTo(adapterPosition)
            }
        }

        fun setViewData(itemData: CurrencyViewModel) {
            binding.setViewModel(itemData)
            binding.executePendingBindings()
        }

    }

    //utils

    private fun getStringByName(context : Context, name:String):String? {
        val identifier: Int = context.resources.getIdentifier(name,"string",context.packageName)
        try {
            return context.resources.getString(identifier)
        } catch (e: Resources.NotFoundException){
            return String.empty()
        }
    }
    private fun getDrawableByName(context : Context, name:String):Int {
        return  context.resources.getIdentifier(name,"drawable",context.packageName)

    }





}
