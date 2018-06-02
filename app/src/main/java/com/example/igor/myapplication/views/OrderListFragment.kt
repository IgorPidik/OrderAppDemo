package com.example.igor.myapplication.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.igor.myapplication.R
import kotlinx.android.synthetic.main.order_list_fragment.*
import kotlinx.android.synthetic.main.order_list_fragment.view.*

class OrderListFragment: Fragment() {

    companion object {
        const val VIEW_VISIBILITY_KEY = "VIEW_VISIBILITY"
    }

    private var adapter: BaseAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_list_fragment, container, false)
        adapter?.let { view.orderList.adapter = it }
        view.orderList.emptyView = view.emptyOrder
        return view
    }

    fun setAdapter(newAdapter: BaseAdapter) {
        adapter = newAdapter
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            if(view != null) {
                view!!.emptyOrder.visibility = savedInstanceState.getInt(VIEW_VISIBILITY_KEY, 0)
            }
        }
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        view?.let {
            outState.putInt(VIEW_VISIBILITY_KEY, it.emptyOrder.visibility)
        }
        super.onSaveInstanceState(outState)
    }


}