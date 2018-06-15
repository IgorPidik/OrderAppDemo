package com.example.igor.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.igor.myapplication.controllers.OrderManager
import com.example.igor.myapplication.models.OrderItem
import kotlinx.android.synthetic.main.order_list_item.view.*

class OrderListAdapter(private val orderManager: OrderManager, context: Context?, private val resource: Int) : ArrayAdapter<OrderItem>(context, resource) {
    private var items = mutableListOf<OrderItem>()

    override fun notifyDataSetChanged() {
        items = orderManager.items
        super.notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = items[position]
        val view = LayoutInflater.from(context).inflate(resource, parent, false)
        view.order_list_item_name.text = item.foodItem.name
        view.order_list_item_count.text = item.count.toString()
        view.order_list_item_price.text = item.foodItem.price.toString()
        view.delete_item.setOnClickListener {
            orderManager.removeItem(item)
        }
        return view
    }
}
