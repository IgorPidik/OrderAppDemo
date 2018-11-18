package com.example.igor.myapplication.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.igor.myapplication.R
import com.example.igor.myapplication.adapters.OrderListAdapter
import com.example.igor.myapplication.controllers.AuthManager
import com.example.igor.myapplication.controllers.OrderManager
import com.example.igor.myapplication.fragments.OrderListFragment
import com.example.igor.myapplication.utils.OrderType
import com.stripe.android.Stripe
import kotlinx.android.synthetic.main.activity_finish_order.*


class FinishOrderActivity : AppCompatActivity(), CanSetCostInterface {
    private val orderManager = OrderManager.instance
    private val authManager = AuthManager.instance
    private val orderListFragment = OrderListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_order)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.title = "Order Summary"
        }

        fillCustomerInformation()
        fillOrderInformation()
        setUpOrderListFragment()

        submit_order.setOnClickListener {
            if (orderManager.items.size == 0)
                Toast.makeText(this, "Your order is empty", Toast.LENGTH_LONG).show()
            else
                submitOrder()
        }


    }

    override fun onResume() {
        super.onResume()
        orderManager.setActivity(this)
        orderManager.notifyChanges()
    }

    private fun submitOrder() {
        val intent = Intent(this, PayActivity::class.java)
        startActivity(intent)
    }

    override fun setNewCost(newCost: Double) {
        order_cost.text = newCost.toString()
    }

    private fun setUpOrderListFragment() {
        val orderListAdapter = OrderListAdapter(orderManager, this, R.layout.order_list_item)

        orderManager.addNotifiableAdapter(orderListAdapter)
        orderListFragment.setAdapter(orderListAdapter)


        if (supportFragmentManager.findFragmentByTag(OrderListFragment.TAG) == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.finish_order_list_frame_layout, orderListFragment, OrderListFragment.TAG)
            transaction.commit()
        }
    }

    private fun fillOrderInformation() {
        val orderType = orderManager.orderType
        delivery_type.text = orderType.toString()
        if (orderType == OrderType.DELIVERY) {
            delivery_address_layout.visibility = View.VISIBLE
            orderManager.address?.let {
                delivery_address.text = it.toString()
            }
        }
    }

    private fun fillCustomerInformation() {
        val customer = authManager.getCustomer()
        customer_first_name.text = customer.firstName
        customer_surname.text = customer.secondName
        customer_phone_number.text = customer.phoneNumber
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
