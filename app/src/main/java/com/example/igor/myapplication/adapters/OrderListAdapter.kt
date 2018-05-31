package com.example.igor.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import com.example.igor.myapplication.R
import com.example.igor.myapplication.controllers.OrderManager
import com.example.igor.myapplication.models.OrderItem
import com.example.igor.myapplication.models.sum
import kotlinx.android.synthetic.main.order_list_item.view.*

class OrderListAdapter(private val orderManager: OrderManager, context: Context?, private val resource: Int) : ArrayAdapter<OrderItem>(context, resource) {
    private var items = mutableListOf<OrderItem>()

    override fun notifyDataSetChanged() {
        super.notifyDataSetChanged()
        items = orderManager.getItems()
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = items[position]
        val view = LayoutInflater.from(context).inflate(resource, parent, false)
        view.orderListItemName.text = item.foodItem.name
        view.orderListItemCount.text = item.count.toString()
        view.orderListItemPrice.text = item.foodItem.price.toString()
        view.deleteItem.setOnClickListener {
            orderManager.removeItem(item)
        }
        return view
    }
}