package com.example.igor.myapplication.controllers

import android.util.Log
import android.widget.BaseAdapter
import com.example.igor.myapplication.adapters.ItemViewAdapter
import com.example.igor.myapplication.models.FoodItem
import com.example.igor.myapplication.models.OrderItem
import com.example.igor.myapplication.models.sum
import com.example.igor.myapplication.activities.MainActivity

class OrderManager private constructor() {
    private var items = mutableListOf<OrderItem>()
    private val adapters = mutableListOf<BaseAdapter>()
    private val recyclerViewAdapters = mutableListOf<ItemViewAdapter>()
    private var activity: MainActivity? = null
    private var price: Double = 0.0

    fun addItem(foodItem: FoodItem) {
        val orderItem = items.find { it.foodItem == foodItem }
        if (orderItem == null) {
            items.add(OrderItem(foodItem, 1))
            Log.d("test", "adding first time")
        } else {
            orderItem.count = orderItem.count + 1
            Log.d("test", "adding ${orderItem.count}th time")
        }
        price += foodItem.price
        notifyChanges()
    }

    fun removeItem(foodItem: FoodItem) {
        val orderItem = items.find { it.foodItem == foodItem }
        if(orderItem == null) return
        else {
            price -= orderItem.foodItem.price
            orderItem.count = orderItem.count - 1
            if (orderItem.count == 0) {
                items.remove(orderItem)
            }
        }
        notifyChanges()
    }

    fun addNotifiableAdapter(adapter: BaseAdapter) {
        adapters.add(adapter)
    }

    fun addNotifiableAdapter(adapter: ItemViewAdapter) {
        recyclerViewAdapters.add(adapter)
    }

    fun removeNotifiableAdapter(adapter: BaseAdapter) {
        adapters.remove(adapter)
    }

    fun removeNotifiableAdapter(adapter: ItemViewAdapter) {
        recyclerViewAdapters.remove(adapter)
    }

    fun cancelOrder() {
        price = 0.0
        items.clear()
    }

    fun getPrice() = price
    fun getItems() = items

    fun notifyChanges() {
        for(adapter in adapters) {
            adapter.notifyDataSetChanged()
        }

        for(adapter in recyclerViewAdapters) {
            adapter.notifyDataSetChanged()
        }

        activity?.setNewCost(price)
    }

    fun setActivity(mainActivity: MainActivity) {
        activity = mainActivity
    }

    fun orderCount(foodItem: FoodItem): Int {
        val orderItem = items.find { it.foodItem == foodItem }
        return if (orderItem == null) return 0
        else {
            orderItem.count
        }
    }

    fun removeItem(item: OrderItem) {
        price -= item.sum()
        items.remove(item)
        notifyChanges()

    }

    companion object {
        val instance: OrderManager by lazy {
            OrderManager()
        }
    }

}