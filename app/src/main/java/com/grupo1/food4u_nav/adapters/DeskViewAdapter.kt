package com.grupo1.food4u_nav.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.grupo1.food4u_nav.ui.home.viewPagerQrCode.BookFragment
import com.grupo1.food4u_nav.ui.home.viewPagerQrCode.NowFragment

class DeskViewAdapter(fragment: Fragment): FragmentStateAdapter(fragment)  {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = NowFragment()
            1 -> fragment = BookFragment()
        }
        return fragment as Fragment
    }
}