package com.grupo1.food4u_nav.ui.home.viewPagerQrCode

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.button.MaterialButton
import com.grupo1.food4u_nav.MainActivity
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.databinding.FragmentNowBinding
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
            fragmentTransaction.replace(com.grupo1.food4u_nav.R.id.containerQr, QRCodeFragment ()).commit()
        }


        val submitTable = parentFragment!!.view!!.findViewById<MaterialButton>(R.id.submitTable)

        val table = view.findViewById<EditText>(R.id.table)

        submitTable.setOnClickListener {
            var mesa = requireContext().getSharedPreferences("Mesa", AppCompatActivity.MODE_PRIVATE)
            val myEdit = mesa.edit()

            if (table.text.isEmpty())
                Toast.makeText(activity, "Preencha o campo", Toast.LENGTH_SHORT).show()
            else{
                myEdit.putInt("id_mesa", table.text.toString().toInt())
                myEdit.apply()
                Toast.makeText(activity, "Registada Mesa " + table.text.toString(), Toast.LENGTH_LONG).show()

                var intent = Intent(requireActivity().applicationContext, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }

        }
        return view
    }
}