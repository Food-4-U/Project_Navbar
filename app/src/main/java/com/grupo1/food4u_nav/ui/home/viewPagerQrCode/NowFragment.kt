package com.grupo1.food4u_nav.ui.home.viewPagerQrCode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.databinding.FragmentFavoritesBinding
import com.grupo1.food4u_nav.databinding.FragmentNowBinding
import com.grupo1.food4u_nav.ui.home.DeskFragment
import com.grupo1.food4u_nav.ui.home.QRCodeFragment

class NowFragment : Fragment() {
    private var _binding: FragmentNowBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_now, container, false)

        val qrCodeBtn = view.findViewById<ImageView>(R.id.qrcodeimage)

        qrCodeBtn.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(com.grupo1.food4u_nav.R.id.containerQr, QRCodeFragment ())
            fragmentTransaction.addToBackStack("null").commit()
        }
        return view
    }
}