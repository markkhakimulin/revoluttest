package com.markkhakimulin.revolut.presentator.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerFragment

/**
 * Created by Mark Khakimulin on 09.09.2019.
 * Email : mark.khakimulin@gmail.com
 */
abstract class BaseFragment<viewDataBinding : ViewDataBinding> : DaggerFragment() {

    private var mActivity : AppCompatActivity? = null

    open lateinit var mBinding: viewDataBinding

    @LayoutRes
    abstract fun getLayoutRes() : Int

    abstract fun onViewInitialized()

    fun getBaseActivity() : AppCompatActivity? = mActivity

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initBinding(inflater,container!!)
        onViewInitialized()
        setHasOptionsMenu(true)
        retainInstance = true
        return mBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mActivity = context as AppCompatActivity
    }

    override fun onDetach() {
        super.onDetach()
        this.mActivity = null
    }
}