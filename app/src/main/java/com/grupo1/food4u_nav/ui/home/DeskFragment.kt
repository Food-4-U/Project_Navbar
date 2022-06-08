package com.grupo1.food4u_nav.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.StringRes
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.adapters.DeskViewAdapter
import com.grupo1.food4u_nav.adapters.ProfileViewPagerAdapter
import com.grupo1.food4u_nav.databinding.FragmentDeskBinding
import com.grupo1.food4u_nav.databinding.FragmentHomeBinding
import com.grupo1.food4u_nav.databinding.FragmentProfileBinding
import com.grupo1.food4u_nav.ui.profile.ProfileFragment

class DeskFragment : Fragment() {

    private var _binding: FragmentDeskBinding? = null

    private val binding get() = _binding!!

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.now,
            R.string.book
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //FIXME Se tirar isso daqui n funciona, pq?
        _binding = FragmentDeskBinding.inflate(inflater, container, false)

        val backBtn =binding.tableBackBtn

        backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val sectionPagerAdapter = DeskViewAdapter(this)
        val viewPager: ViewPager2 = binding.deskViewPager
        viewPager.adapter = sectionPagerAdapter


        val tabs: TabLayout = binding.deskTabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(DeskFragment.TAB_TITLES[position])
        }.attach()

        if(tabs.isActivated){

        }

        // Inflate the layout for this fragment
        return binding.root
    }


}