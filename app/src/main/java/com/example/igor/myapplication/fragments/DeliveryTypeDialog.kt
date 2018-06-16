package com.example.igor.myapplication.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.example.igor.myapplication.R
import com.example.igor.myapplication.controllers.OrderManager
import com.example.igor.myapplication.utils.OrderType
import kotlinx.android.synthetic.main.order_type_dialog.*

class DeliveryTypeDialog : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_type_dialog, container, false)
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val orderManager = OrderManager.instance
        order_type_collection.setOnClickListener {
            orderManager.orderType = OrderType.COLLECTION
            dialog.dismiss()
        }

        order_type_delivery.setOnClickListener {
            orderManager.orderType = OrderType.DELIVERY
            dialog.dismiss()
        }

    }
}