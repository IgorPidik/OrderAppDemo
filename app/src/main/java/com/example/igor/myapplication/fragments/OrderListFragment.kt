package com.example.igor.myapplication.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.igor.myapplication.R
import kotlinx.android.synthetic.main.order_list_fragment.view.*

class OrderListFragment: Fragment() {

    companion object {
        const val VIEW_VISIBILITY_KEY = "VIEW_VISIBILITY"
        val TAG = OrderListFragment::class.java.simpleName
    }

    private var adapter: BaseAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_list_fragment, container, false)
        adapter?.let { view.order_list.adapter = it }
        view.order_list.emptyView = view.empty_order
        return view
    }

    fun setAdapter(newAdapter: BaseAdapter) {
        adapter = newAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
}