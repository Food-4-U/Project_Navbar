package com.grupo1.food4u_nav.ui.profile.viewPager


import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.databinding.FragmentSettingsBinding
import com.grupo1.food4u_nav.ui.profile.viewPager.settings.AdditionalForm
import java.util.*


class Settings : Fragment() {

    private lateinit var _binding: FragmentSettingsBinding

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val btnForm : TextView = root.findViewById(com.grupo1.food4u_nav.R.id.profile_additionalForm)

        val changeLanguageEn : TextView = root.findViewById(R.id.changeLanguageEN)
        val changeLanguagePT : TextView = root.findViewById(R.id.changeLanguagePT)


        changeLanguagePT.setOnClickListener {
            if(Locale.getDefault().language != changeLanguageEn.text)
                changeLanguage(changeLanguagePT.text.toString(),resources,requireActivity())
        }

        changeLanguageEn.setOnClickListener {
           if(Locale.getDefault().language != changeLanguageEn.text)
               changeLanguage(changeLanguageEn.text.toString(),resources,requireActivity())
        }

        btnForm.setOnClickListener{
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(com.grupo1.food4u_nav.R.id.container, AdditionalForm())
            fragmentTransaction.addToBackStack("null").commit()
        }

        return root
    }
}
    @Suppress("DEPRECATION")
    fun changeLanguage(lg: String, res: Resources, context: Context) {
        val config = res.configuration
        val locale = Locale(lg)
        Locale.setDefault(locale)
        config.locale = locale
        res.updateConfiguration(config, res.displayMetrics)

        val intent = Intent(context, context::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        context.startActivity(intent)
    }
