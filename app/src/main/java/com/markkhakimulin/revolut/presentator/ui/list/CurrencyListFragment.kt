package com.markkhakimulin.revolut.presentator.ui.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.markkhakimulin.revolut.R
import com.markkhakimulin.revolut.databinding.CurrencyListFragmentBinding
import com.markkhakimulin.revolut.presentator.base.BaseFragment
import com.markkhakimulin.revolut.presentator.base.ViewModelFactory
import com.markkhakimulin.revolut.presentator.ui.MainActivity
import javax.inject.Inject


/**
 * Created by Mark Khakimulin on 09.09.2019.
 * Email : mark.khakimulin@gmail.com
 */
class CurrencyListFragment : BaseFragment<CurrencyListFragmentBinding>() {

    @Inject
    lateinit var mVMFactory : ViewModelFactory
    private val mViewModel  : CurrencyListViewModel by lazy {
        ViewModelProviders.of(this,mVMFactory).get(CurrencyListViewModel::class.java)
    }

    override fun getLayoutRes(): Int  =  R.layout.currency_list_fragment

    override fun onViewInitialized() {

        this.mBinding.listViewModel = mViewModel
        this.mBinding.lifecycleOwner = this
        this.initRecycler()
        this.listenErrors()
    }

    private fun initRecycler(){

        this.mBinding.recyclerView
            .apply {
                adapter = CurrencyAdapter(mViewModel)
                layoutManager = LinearLayoutManager(getBaseActivity(), RecyclerView.VERTICAL, false)
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (getBaseActivity() as MainActivity).displayHomeAsUpEnabled(false)
        mViewModel.getLatest()
    }

    private fun listenErrors() {
        mViewModel.error
            .observe(this, Observer {  Toast.makeText(context, it, Toast.LENGTH_SHORT).show() })
    }
}