package com.example.igor.myapplication.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.igor.myapplication.R
import com.example.igor.myapplication.adapters.ItemViewAdapter
import com.example.igor.myapplication.controllers.OrderManager
import com.example.igor.myapplication.models.FoodItem
import kotlinx.android.synthetic.main.fragment_page.view.*

class PageFragment : Fragment() {

    private val page: Int by lazy {
        arguments!!.getInt("PAGE", 0)
    }
    private val pageTitle: String by lazy {
        arguments!!.getString("PAGE_NAME")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val orderManager = OrderManager.instance
        val view = inflater.inflate(R.layout.fragment_page, container, false)
        view.pageHeading.text = pageTitle

        val items = mutableListOf<FoodItem>()
        for(i in 1 until 20)
            items.add(FoodItem("$pageTitle $i", "Description $i", i.toDouble()))

        Log.d("test", "test")
        view.itemsView.layoutManager = LinearLayoutManager(activity)
        val itemAdapter = ItemViewAdapter(items, orderManager)
        orderManager.addNotifiableAdapter(itemAdapter)
        view.itemsView.adapter = itemAdapter

        return view
    }



}