package com.example.run.fragments.Api.corrida.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.run.R
import com.example.run.api.dto.CorridasDto
import com.example.run.api.request.CorridasApi
import com.example.run.api.retrofit.ServiceBuilder
import com.example.run.databinding.FragmentAddCorridaBinding

import com.example.run.fragments.corrida.add.DatePickerFragment
import com.example.run.model.Corrida
import com.example.run.utils.utils.Companion.getToken
import com.example.run.utils.utils.Companion.getUserIdInSession
import com.example.run.utils.utils.Companion.hideKeyboard
import com.example.run.utils.utils.Companion.somethingWentWrong
import com.example.run.utils.utils.Companion.unauthorized
import kotlinx.android.synthetic.main.fragment_add_corrida.*
import kotlinx.android.synthetic.main.fragment_add_corrida.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class addCorrida : Fragment() {

private val args by navArgs<addCorridaArgs>()

private var _binding: FragmentAddCorridaBinding? = null
private val binding get() = _binding!!
override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    // Inflate the layout for this fragment
    val view = inflater.inflate(R.layout.fragment_add_corrida, container, false)



    _binding = FragmentAddCorridaBinding.bind(view)

    binding.apply {
        btnSelectDate.setOnClickListener {
            hideKeyboard()
            // create new instance of DatePickerFragment
            val datePickerFragment = DatePickerFragment()
            val supportFragmentManager = requireActivity().supportFragmentManager

            // we have to implement setFragmentResultListener
            supportFragmentManager.setFragmentResultListener(
                "REQUEST_KEY",
                viewLifecycleOwner
            ) { resultKey, bundle ->
                if (resultKey == "REQUEST_KEY") {
                    val date = bundle.getString("SELECTED_DATE")
                    tvSelectedDate.text = date
                }
            }

            // show
            datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
        }
    }
    view.add_btn_corrida.setOnClickListener {
       //Toast.makeText(requireContext(), args.ola123.firstName, Toast.LENGTH_LONG).show()
      addCorrida(args.currentCorrida.id)
    }
    return view
}


private fun inputCheck(hinicio: String, hfim: String, data: String, km: Int): Boolean{
    return (TextUtils.isEmpty(hinicio) && TextUtils.isEmpty(hfim) && TextUtils.isEmpty(data) && TextUtils.isEmpty(km.toString()))
}






    private fun addCorrida(id:Int) {
        val hinicio= et_hinicio.text.toString()
        val hfim = et_hfim.text.toString()
        val data = tvSelectedDate.text.toString()
        val km:Int = et_km.text.toString().toInt()

        if(inputCheck(hinicio, hfim, data,km)){
            Toast.makeText(
                requireContext(),
                getString(R.string.fill_all),
                Toast.LENGTH_LONG
            )
                .show()
        } else {
            llProgressBar.bringToFront()
            llProgressBar.visibility = View.VISIBLE

            val request = ServiceBuilder.buildService(CorridasApi::class.java)
            val call = request.createCorrida(
                token = "Bearer ${getToken()}",
                users_id = getUserIdInSession(),
                km = km,
                id_user = id,
                hora_inicio = hinicio,
                hora_fim = hfim,
                data = data
            )

            call.enqueue(object : Callback<CorridasDto> {
                override fun onResponse(call: Call<CorridasDto>, response: Response<CorridasDto>) {
                    llProgressBar.visibility = View.GONE

                    if (response.isSuccessful) {
                        val report: CorridasDto = response.body()!!

                        if (report.status == "OK") {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.successfull_added),
                                Toast.LENGTH_LONG
                            ).show()
                            val action = addCorridaDirections.actionAddCorridaFragmentToListarCorridaFragment(args.currentCorrida)
                            findNavController().navigate(action)
                        } else {
                            Toast.makeText(
                                requireContext(), getString(
                                    resources.getIdentifier(
                                        report.message, "string",
                                        context?.packageName
                                    )
                                ), Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        if (response.code() == 401) {
                            unauthorized(navigatonHandlder = {
                                findNavController().navigate(R.id.action_addCorridaFragment_to_loginFragment)
                            })
                        } else {
                            somethingWentWrong()
                        }
                    }
                }

                override fun onFailure(call: Call<CorridasDto>, t: Throwable) {
                    llProgressBar.visibility = View.GONE
                    somethingWentWrong()
                }
            })
        }
}}


