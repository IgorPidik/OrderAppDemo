package com.example.igor.myapplication.adapters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.igor.myapplication.fragments.PageFragment

class ItemFragmentPageAdapter(private val items: List<String>, fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        val fragment = PageFragment()
        val args = Bundle()
        args.putInt("PAGE", position+1)
        args.putString("PAGE_NAME", items[position])
        fragment.arguments = args
        return fragment
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return items[position]
    }
}