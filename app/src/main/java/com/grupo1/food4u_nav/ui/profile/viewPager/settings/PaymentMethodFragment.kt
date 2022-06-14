package com.grupo1.food4u_nav.ui.profile.viewPager.settings

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo1.food4u_nav.FinishOrderActivity
import com.grupo1.food4u_nav.OrderActivity
import com.grupo1.food4u_nav.R
import com.grupo1.food4u_nav.databinding.FragmentPaymentMethodBinding
import com.grupo1.food4u_nav.models.CardNumber
import com.grupo1.food4u_nav.models.ItensPedido
import com.grupo1.food4u_nav.models.Pedido
import com.grupo1.food4u_nav.models.data.CartDatabase
import com.grupo1.food4u_nav.ui.home.DeskFragment
import java.text.SimpleDateFormat
import java.util.*


class PaymentMethodFragment : Fragment() {

    var card : List<CardNumber> = arrayListOf()

    private var _binding: FragmentPaymentMethodBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPaymentMethodBinding.inflate(inflater, container, false)

        val backPayButton = binding.backButtonPayment
        val cCard = binding.imageView25
        val mbWay = binding.imageView27
        val payPal = binding.imageView29
        val counter = binding.imageView32
        val payButton = binding.continueOrder


        var cCardIsChecked = false
        var mbWayIsChecked = false
        var payPalIsChecked = false
        var counterIsChecked = false

        val rv_card: RecyclerView = binding.rvCard
        val CardNumber = CardNumber (requireActivity(),card)

        rv_topRated.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        rv_topRated.adapter = adapterTopRated

        //addCard.setOnClickListener{
        //    val activity = requireView().context as AppCompatActivity
        //    val myFragment: Fragment = CardFragment()
        //    activity.supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
        //        .replace(R.id.paymentMethodFragment, myFragment).addToBackStack(null).commit()
        //}


        backPayButton.setOnClickListener{
            val i = Intent(activity, OrderActivity::class.java)
            startActivity(i)
        }

        cCard.setOnClickListener {
            mbWay.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_15)
            payPal.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_14)
            counter.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_11)
            cCard.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_16)

            cCardIsChecked = true
            mbWayIsChecked = false
            payPalIsChecked = false
            counterIsChecked = false


        }

        mbWay.setOnClickListener{
            cCard.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_80)
            mbWay.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_18)
            counter.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_11)
            payPal.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_14)

            cCardIsChecked = false
            mbWayIsChecked = true
            payPalIsChecked = false
            counterIsChecked = false


        }

        payPal.setOnClickListener {
            cCard.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_80)
            mbWay.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_15)
            counter.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_11)
            payPal.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_19)

            cCardIsChecked = false
            mbWayIsChecked = false
            payPalIsChecked = true
            counterIsChecked = false

        }

        counter.setOnClickListener {
            cCard.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_80)
            mbWay.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_15)
            payPal.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_14)
            counter.setImageResource(com.grupo1.food4u_nav.R.drawable.grupo_79)

            cCardIsChecked = false
            mbWayIsChecked = false
            payPalIsChecked = false
            counterIsChecked = true


        }





        payButton.setOnClickListener {
            if (cCardIsChecked) {

                var pedido = Pedido(null, null, null, null, null,
                    null, null, null)

                var dataFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                var date = dataFormat.format(Date().time)
                pedido.dataHora = date
                pedido.pago = true
                pedido.id_mesa = requireContext().getSharedPreferences("Mesa", AppCompatActivity.MODE_PRIVATE).getInt("id_mesa", 0)
                pedido.id_cliente = requireContext().getSharedPreferences("Cliente", AppCompatActivity.MODE_PRIVATE).getInt("id", 0)
                pedido.total = requireContext().getSharedPreferences("Total", AppCompatActivity.MODE_PRIVATE).getFloat("price",0.0F).toDouble()

                if (pedido.id_mesa == 0) {
                    val fragmentManager = (activity as FragmentActivity).supportFragmentManager
                    val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
                    fragmentTransaction.replace(R.id.containerOrder, DeskFragment())
                    fragmentTransaction.addToBackStack(null).commit()

                } else {
                    Backend.addPedido(pedido) {
                        if (!it) {
                            Toast.makeText(
                                requireActivity(),
                                "Erro.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        var cliente = requireContext().getSharedPreferences("Cliente", AppCompatActivity.MODE_PRIVATE).getInt("id", 0)

                        Backend.GetPedidosDataCliente(cliente, date) {
                            var itensPedido = ItensPedido (null, null, null, null)
                            itensPedido.id_pedido = it.id_pedido

                            CartDatabase.getDatabase(requireActivity()).cartDao().readCart().observe(requireActivity(), androidx.lifecycle.Observer {

                                //este valor tem de ir para cart fora
                                var cart = it

                                for (i in 1..cart.size) {
                                    itensPedido.id_pedido = pedido.id_pedido
                                    itensPedido.id_item = cart[i - 1].item_id
                                    itensPedido.qtd = cart[i - 1].quantidade

                                    Backend.addItemPedido(itensPedido) {
                                        if (it) {
                                            Toast.makeText(
                                                requireActivity(),
                                                "Pedido feito com sucesso,",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                            })
                        }
                    }

                    val i = Intent(activity, FinishOrderActivity::class.java)
                    startActivity(i)
                }

            }

            else if (mbWayIsChecked) {

                var pedido = Pedido(null, null, null, null, null,
                    null, null, null)

                var dataFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                var date = dataFormat.format(Date().time)
                pedido.dataHora = date
                pedido.pago = true
                pedido.id_mesa = requireContext().getSharedPreferences("Mesa", AppCompatActivity.MODE_PRIVATE).getInt("id_mesa", 0)
                pedido.id_cliente = requireContext().getSharedPreferences("Cliente", AppCompatActivity.MODE_PRIVATE).getInt("id", 0)
                pedido.total = requireContext().getSharedPreferences("Total", AppCompatActivity.MODE_PRIVATE).getFloat("price",0.0F).toDouble()

                if (pedido.id_mesa == 0) {
                    val fragmentManager = (activity as FragmentActivity).supportFragmentManager
                    val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
                    fragmentTransaction.replace(R.id.containerOrder, DeskFragment())
                    fragmentTransaction.addToBackStack(null).commit()

                } else {
                    Backend.addPedido(pedido) {
                        if (!it) {
                            Toast.makeText(
                                requireActivity(),
                                "Erro.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    val i = Intent(activity, FinishOrderActivity::class.java)
                    startActivity(i)
                }
            }

            else if (payPalIsChecked) {

                var pedido = Pedido(null, null, null, null, null,
                    null, null, null)

                var dataFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                var date = dataFormat.format(Date().time)
                pedido.dataHora = date
                pedido.pago = true
                pedido.id_mesa = requireContext().getSharedPreferences("Mesa", AppCompatActivity.MODE_PRIVATE).getInt("id_mesa", 0)
                pedido.id_cliente = requireContext().getSharedPreferences("Cliente", AppCompatActivity.MODE_PRIVATE).getInt("id", 0)
                pedido.total = requireContext().getSharedPreferences("Total", AppCompatActivity.MODE_PRIVATE).getFloat("price",0.0F).toDouble()

                if (pedido.id_mesa == 0) {
                    val fragmentManager = (activity as FragmentActivity).supportFragmentManager
                    val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
                    fragmentTransaction.replace(R.id.containerOrder, DeskFragment())
                    fragmentTransaction.addToBackStack(null).commit()

                } else {
                    Backend.addPedido(pedido) {
                        if (!it) {
                            Toast.makeText(
                                requireActivity(),
                                "Erro.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    val i = Intent(activity, FinishOrderActivity::class.java)
                    startActivity(i)
                }

            }

            else if (counterIsChecked) {

                var pedido = Pedido(null, null, null, null, null,
                    null, null, null)

                var dataFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                var date = dataFormat.format(Date().time)
                pedido.dataHora = date
                pedido.pago = true
                pedido.id_mesa = requireContext().getSharedPreferences("Mesa", AppCompatActivity.MODE_PRIVATE).getInt("id_mesa", 0)
                pedido.id_cliente = requireContext().getSharedPreferences("Cliente", AppCompatActivity.MODE_PRIVATE).getInt("id", 0)
                pedido.total = requireContext().getSharedPreferences("Total", AppCompatActivity.MODE_PRIVATE).getFloat("price",0.0F).toDouble()

                if (pedido.id_mesa == 0) {
                    val fragmentManager = (activity as FragmentActivity).supportFragmentManager
                    val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
                    fragmentTransaction.replace(R.id.containerOrder, DeskFragment())
                    fragmentTransaction.addToBackStack(null).commit()

                } else {
                    Backend.addPedido(pedido) {
                        if (!it) {
                            Toast.makeText(
                                requireActivity(),
                                "Erro.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    Toast.makeText(
                        requireActivity(),
                        "Aguarde Funcionário.",
                        Toast.LENGTH_SHORT
                    ).show()

                    val i = Intent(activity, FinishOrderActivity::class.java)
                    startActivity(i)
                }

            } else {
                Toast.makeText(
                    requireActivity(),
                    "Por favor selecione uma opção.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val prefs : SharedPreferences? = activity?.getSharedPreferences("Total",
            Context.MODE_PRIVATE
        )

        var totalPrice = prefs?.getString("preço", "")
        var textPrice = binding.totalPaymentMoney

        textPrice.text = totalPrice.toString()

        // Inflate the layout for this fragment
        return binding.root
    }
}