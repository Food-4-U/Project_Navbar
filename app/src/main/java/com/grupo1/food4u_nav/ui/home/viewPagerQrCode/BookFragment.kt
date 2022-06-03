package com.grupo1.food4u_nav.ui.home.viewPagerQrCode

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.grupo1.food4u_nav.R
import java.text.SimpleDateFormat
import java.util.*


class BookFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_book, container, false)
        val datePicker = view.findViewById<TextView>(R.id.btn_dateTimePicker)
        val timePicker = view.findViewById<TextView>(R.id.btn_TimePicker)

        val calendar = Calendar.getInstance()


        datePicker.setOnClickListener{
            var datePickerDialog = DatePickerDialog(
                requireActivity(),{ view, year, month, dayOfMonth ->
                    val dataSelect = Calendar.getInstance()
                   // dataSelect[year, month] = dayOfMonth //FIXME ???

                    val format = SimpleDateFormat("dd/MM/yyyy")

                    datePicker.text = format.format(dataSelect.time)

                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

        timePicker.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                requireActivity(),
                TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                    calendar.set(Calendar.HOUR_OF_DAY, hour)
                    calendar.set(Calendar.MINUTE, minute)
                    timePicker.text = SimpleDateFormat("HH:mm").format(calendar.time)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }

        return  view
    }


}