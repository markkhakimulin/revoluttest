package com.markkhakimulin.revolut.presentator.databinding

import android.content.Context
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.markkhakimulin.revolut.R
import com.markkhakimulin.revolut.presentator.ui.list.CurrencyViewModel

/**
 * Created by Mark Khakimulin on 13.09.2019.
 * Email : mark.khakimulin@gmail.com
 */
class BindingAdapters {
    companion object {

        @JvmStatic
        @BindingAdapter("android:bindItems")
        fun bindItems(view: RecyclerView, items: List<CurrencyViewModel>?){
            items?.apply {
                view.adapter?.also { adapter ->
                    adapter.notifyDataSetChanged()
                }
            }
        }
        @JvmStatic
        @BindingAdapter("android:src")
        fun setImageResource(view: ImageView, img: Int) {
            Glide.with(view.context).load(img).apply(RequestOptions.circleCropTransform()).into(view)
            //view.setImageResource(img)
        }

        @BindingAdapter("android:hideKeyboardOnFocusDone")
        @JvmStatic
        fun hideKeyboardOnInputDone(view: EditText, enabled: Boolean) {
            if (!enabled) {
                view.setOnFocusChangeListener(null)
                return
            }
            view.setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE)
                            as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }
            }

        }
    }
}