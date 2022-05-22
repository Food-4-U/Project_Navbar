package com.grupo1.food4u_nav.ui.profile

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.grupo1.food4u_nav.LoginActivity
import com.grupo1.food4u_nav.MainActivity
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.RegisterActivity
import com.grupo1.food4u_nav.adapters.ProfileViewPagerAdapter
import com.grupo1.food4u_nav.databinding.FragmentProfileBinding
import com.grupo1.food4u_nav.models.Cliente


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.orders,
            R.string.wallet,
            R.string.favorites,
            R.string.settings
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val sectionPagerAdapter = ProfileViewPagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionPagerAdapter

        val prefs : SharedPreferences? = activity?.getSharedPreferences("Cliente", MODE_PRIVATE)

        val cliente : Cliente = Cliente(email = null, id_cliente = null, password = null, nome = null)
        cliente.email = prefs?.getString("email", "")
        cliente.nome = prefs?.getString("nome", "")

        val clientName = binding.textView5
        val clientEmail = binding.textView7

        clientName.text = cliente.nome
        clientEmail.text = cliente.email

        val myEdit = prefs?.edit()
        val btnLogout : Button = binding.btnLogout

        btnLogout.setOnClickListener {
            prefs?.edit()?.clear()?.apply();
            myEdit?.apply()

            val intent = Intent(activity, LoginActivity::class.java);
            startActivity(intent)
            activity?.finish()
        }



        val tabs: TabLayout = binding.profileTabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        val selectedTab: TabLayout.Tab? = tabs.getTabAt(0)
        selectedTab?.let { activity?.let { ResourcesCompat.getFont(it, R.font.segoeui) }
            ?.let { it1 -> setTabTypeface(it, it1) } }

        tabs.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                ResourcesCompat.getFont(tab.view.context, R.font.segoeuib)
                    ?.let { setTabTypeface(tab, it) }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                ResourcesCompat.getFont(tab.view.context, R.font.segoeuisl)?.let {
                    setTabTypeface(
                        tab,
                        it
                    )
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        return binding.root

    }

    private fun setTabTypeface(tab: TabLayout.Tab, typeface: Typeface) {
        for (i in 0 until tab.view.childCount) {
            val tabViewChild = tab.view.getChildAt(i)
            if (tabViewChild is TextView) tabViewChild.typeface = typeface
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}