package com.example.igor.myapplication.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.ViewGroup
import com.example.igor.myapplication.R
import com.example.igor.myapplication.adapters.ItemFragmentPageAdapter
import com.example.igor.myapplication.adapters.OrderListAdapter
import com.example.igor.myapplication.controllers.OrderManager
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val orderManager = OrderManager.instance


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
        orderList.adapter = orderListAdapter



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

    override fun onResume() {
        super.onResume()
        setNewCost(orderManager.getPrice())
    }

    fun setNewCost(newCost: Double) {
        orderPrice.text = newCost.toString()
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
