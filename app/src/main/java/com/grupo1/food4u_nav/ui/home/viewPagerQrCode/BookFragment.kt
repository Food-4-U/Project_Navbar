package com.grupo1.food4u_nav.ui.home.viewPagerQrCode

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import com.grupo1.food4u_nav.R
import java.lang.reflect.Array.newInstance
import java.util.*


class BookFragment : Fragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0

    var te = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_book, container, false)
        val datePicker = view.findViewById<TextView>(R.id.btn_dateTimePicker)
        pickDate(datePicker)

        return  view
    }

    private fun pickDate(btn_timePicker : TextView){
        btn_timePicker.setOnClickListener{
            getDateTime()
            DatePickerDialog(requireActivity(),this,year,month,day).show()
        }
    }

    private fun getDateTime(){
        val cal = Calendar.getInstance()

        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)

        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year

        getDateTime()

        TimePickerDialog(requireActivity(),this,hour, minute,true).show()
    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        savedHour = hour
        savedMinute = minute


        Toast.makeText(requireActivity(),
            "Dia $savedDay/$savedMonth/$savedYear Hora $savedHour:$savedMinute",
            Toast.LENGTH_SHORT).show()
    }

    private fun setInfo(textview: TextView)
    {
        textview.text = "$savedDay/$savedMonth/$savedYear $savedHour:$savedMinute"
    }

}