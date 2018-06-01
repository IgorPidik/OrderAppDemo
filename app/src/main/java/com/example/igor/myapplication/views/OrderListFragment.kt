package com.example.igor.myapplication.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.igor.myapplication.R
import kotlinx.android.synthetic.main.order_list_fragment.*
import kotlinx.android.synthetic.main.order_list_fragment.view.*

class OrderListFragment: Fragment() {
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


}