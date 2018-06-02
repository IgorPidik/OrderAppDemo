package com.example.igor.myapplication.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import com.example.igor.myapplication.R
import com.example.igor.myapplication.adapters.ItemFragmentPageAdapter
import com.example.igor.myapplication.adapters.OrderListAdapter
import com.example.igor.myapplication.controllers.OrderManager
import com.example.igor.myapplication.fragments.OrderListFragment
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val orderManager = OrderManager.instance
    private val orderListFragment = OrderListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuList = listOf("Deserts", "Drinks", "Dips", "Pizza", "Classic Sides", "Premiums Sides", "COMB")

        viewpager.adapter = ItemFragmentPageAdapter(menuList, supportFragmentManager)
        tabLayout.setupWithViewPager(viewpager)
        setUpTableLayoutMode()
        orderManager.setActivity(this)
        val orderListAdapter = OrderListAdapter(orderManager, this, R.layout.order_list_item)

        orderManager.addNotifiableAdapter(orderListAdapter)
        orderListFragment.setAdapter(orderListAdapter)


        setUpOrderListFragment()


        finishOrder.setOnClickListener {
            if(orderManager.getItems().size == 0)
                Toast.makeText(this, "Order something first", Toast.LENGTH_LONG).show()
            else
                finishOrder()
        }

    }

    private fun setUpTableLayoutMode() {
        tabLayout.post {
            if(tabLayout.width < resources.displayMetrics.widthPixels) {
                tabLayout.tabMode = TabLayout.MODE_FIXED
                val params = tabLayout.layoutParams
                params.width = ViewGroup.LayoutParams.MATCH_PARENT
                tabLayout.layoutParams = params
            } else {
                tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
            }
        }
    }

    private fun setUpOrderListFragment() {
        if(supportFragmentManager.findFragmentByTag(OrderListFragment.TAG) == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.orderListFrame, orderListFragment, OrderListFragment.TAG)
            transaction.commit()
        }
    }

    override fun onResume() {
        super.onResume()
        orderManager.notifyChanges()
    }

    fun setNewCost(newCost: Double) {
        orderPrice.text = newCost.toString()
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
            super.onBackPressed()
        }
    }
}
