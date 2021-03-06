package com.grupo1.food4u_nav.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.grupo1.food4u_nav.MainActivity
import com.grupo1.food4u_nav.R

class QRCodeFragment : Fragment() {

    private lateinit var codeScanner: CodeScanner

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        if(ContextCompat.checkSelfPermission(requireActivity(),Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA),123)
        }

        return inflater.inflate(R.layout.fragment_qrcode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val scannerView = view.findViewById<CodeScannerView>(R.id.scanner_view)
        val activity = requireActivity()
        codeScanner = CodeScanner(activity, scannerView)

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }

        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                var mesa = requireContext().getSharedPreferences("Mesa", AppCompatActivity.MODE_PRIVATE)
                val myEdit = mesa.edit()

                val string = it.text

                myEdit.putInt("id_mesa", string.toInt())
                myEdit.apply()
                Toast.makeText(activity, "Registada Mesa " + string, Toast.LENGTH_LONG).show()

                var intent = Intent(requireActivity().getApplicationContext(), MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }

        codeScanner.errorCallback = ErrorCallback {
            activity.runOnUiThread {
                Toast.makeText(activity,"Camera Inicialiation Error!", Toast.LENGTH_SHORT).show()
            }
        }



    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}


