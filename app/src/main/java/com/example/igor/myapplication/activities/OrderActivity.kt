package com.example.igor.myapplication.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.ViewGroup
import android.widget.Toast
import com.example.igor.myapplication.R
import com.example.igor.myapplication.adapters.ItemFragmentPageAdapter
import com.example.igor.myapplication.adapters.OrderListAdapter
import com.example.igor.myapplication.controllers.OrderManager
import com.example.igor.myapplication.fragments.OrderListFragment
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_main.*

class OrderActivity : AppCompatActivity(), CanSetCostInterface {
    private val orderManager = OrderManager.instance
    private val orderListFragment = OrderListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuList = listOf("Deserts", "Drinks", "Dips", "Pizza", "Classic Sides", "Premiums Sides", "COMB")

        view_pager.adapter = ItemFragmentPageAdapter(menuList, supportFragmentManager)
        tab_layout.setupWithViewPager(view_pager)
        setUpTableLayoutMode()
        orderManager.setActivity(this)
        val orderListAdapter = OrderListAdapter(orderManager, this, R.layout.order_list_item)

        orderManager.addNotifiableAdapter(orderListAdapter)
        orderListFragment.setAdapter(orderListAdapter)


        setUpOrderListFragment()


        finish_order.setOnClickListener {
            if(orderManager.items.size == 0)
                Toast.makeText(this, "Order something first", Toast.LENGTH_LONG).show()
            else
                finishOrder()
        }

    }

    private fun setUpTableLayoutMode() {
        tab_layout.post {
            if(tab_layout.width < resources.displayMetrics.widthPixels) {
                tab_layout.tabMode = TabLayout.MODE_FIXED
                val params = tab_layout.layoutParams
                params.width = ViewGroup.LayoutParams.MATCH_PARENT
                tab_layout.layoutParams = params
            } else {
                tab_layout.tabMode = TabLayout.MODE_SCROLLABLE
            }
        }
    }

    private fun setUpOrderListFragment() {
        if(supportFragmentManager.findFragmentByTag(OrderListFragment.TAG) == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.order_list_frame, orderListFragment, OrderListFragment.TAG)
            transaction.commit()
        }
    }

    override fun onResume() {
        super.onResume()
        orderManager.setActivity(this)
        orderManager.notifyChanges()
    }

    override fun setNewCost(newCost: Double) {
        order_price.text = newCost.toString()
    }

    private fun finishOrder() {
        val intent = Intent(this, FinishOrderActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        if(slidingLayout.panelState == SlidingUpPanelLayout.PanelState.EXPANDED
                ||slidingLayout.panelState == SlidingUpPanelLayout.PanelState.ANCHORED) {
            slidingLayout.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        } else {
            orderManager.cancelOrder()
            super.onBackPressed()
        }
    }
}
