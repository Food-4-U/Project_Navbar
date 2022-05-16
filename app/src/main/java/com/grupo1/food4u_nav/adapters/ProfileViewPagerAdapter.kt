package com.grupo1.food4u_nav.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.grupo1.food4u_nav.ui.profile.viewPager.Favorites
import com.grupo1.food4u_nav.ui.profile.viewPager.Order
import com.grupo1.food4u_nav.ui.profile.viewPager.Settings
import com.grupo1.food4u_nav.ui.profile.viewPager.Wallet

class ProfileViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = Order()
            1 -> fragment = Wallet()
            2 -> fragment = Favorites()
            3 -> fragment = Settings()
        }
        return fragment as Fragment
    }
}