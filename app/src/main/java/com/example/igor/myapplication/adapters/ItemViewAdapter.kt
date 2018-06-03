package com.example.igor.myapplication.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.igor.myapplication.R
import com.example.igor.myapplication.controllers.OrderManager
import com.example.igor.myapplication.models.FoodItem
import kotlinx.android.synthetic.main.basic_food_item.view.*

class ItemViewAdapter(private val items: List<FoodItem>, private val orderManager: OrderManager) : RecyclerView.Adapter<ItemViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.basic_food_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (foodName, foodDescription, price) = items[position]
        holder.view.item_name.text = foodName
        holder.view.item_description.text = foodDescription
        holder.view.item_price.text = price.toString()
        setOrderCount(holder.view.order_count, items[position])
        holder.view.add_item.setOnClickListener {
            orderManager.addItem(items[position])
            setOrderCount(holder.view.order_count, items[position])
        }
        holder.view.remove_item.setOnClickListener {
            orderManager.removeItem(items[position])
            setOrderCount(holder.view.order_count, items[position])
        }
    }

    private fun setOrderCount(orderCount: TextView, foodItem: FoodItem) {
        orderCount.text = orderManager.orderCount(foodItem).toString()
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}