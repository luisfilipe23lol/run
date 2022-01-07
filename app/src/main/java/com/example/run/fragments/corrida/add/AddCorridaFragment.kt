package com.example.run.fragments.corrida.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.run.R
import com.example.run.databinding.FragmentAddCorridaBinding
import com.example.run.model.Corrida
import com.example.run.utils.utils.Companion.hideKeyboard
import com.example.run.viewmodel.CorridaViewModel
import kotlinx.android.synthetic.main.fragment_add_corrida.*
import kotlinx.android.synthetic.main.fragment_add_corrida.view.*


class addCorridaFragment : Fragment() {
    /*private val args by navArgs<addCorridaFragmentArgs>()
    private lateinit var mCorridaViewModel: CorridaViewModel
    private var _binding: FragmentAddCorridaBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_corrida, container, false)

        mCorridaViewModel = ViewModelProvider(this).get(CorridaViewModel::class.java)

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

    private fun addCorrida(id:Int) {
        val hinicio= et_hinicio.text.toString()
        val hfim = et_hfim.text.toString()
        val data = tvSelectedDate.text.toString()
        val km:Int = et_km.text.toString().toInt()

        if(inputCheck(hinicio, hfim, data,km)){
            // Create User Object
            val corrida = Corrida( 0,km, id, hinicio, hfim, data)
            // Add Data to Database
            mCorridaViewModel.addCorrida(corrida)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            val action = addCorridaFragmentDirections.actionAddCorridaFragmentToListarCorridaFragment(args.currentCorrida)
            findNavController().navigate(action)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(hinicio: String, hfim: String, data: String, km: Int): Boolean{
        return !(TextUtils.isEmpty(hinicio) && TextUtils.isEmpty(hfim) && TextUtils.isEmpty(data) && TextUtils.isEmpty(km.toString()))
    }
*/
}