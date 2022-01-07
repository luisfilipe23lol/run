package com.example.run.fragments.Api.corrida.list

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.run.R
import com.example.run.api.models.corridas
import com.example.run.api.request.CorridasApi

import com.example.run.api.retrofit.ServiceBuilder
import com.example.run.fragments.Api.corrida.add.addCorridaArgs
import com.example.run.fragments.corrida.add.addCorridaFragment


import com.example.run.utils.utils.Companion.getToken
import com.example.run.utils.utils.Companion.getUserIdInSession
import com.example.run.utils.utils.Companion.somethingWentWrong
import com.example.run.utils.utils.Companion.unauthorized
import kotlinx.android.synthetic.main.fragment_listar_corrida.*
import kotlinx.android.synthetic.main.fragment_listar_corrida.view.*
import kotlinx.android.synthetic.main.fragment_listar_corrida.view.llProgressBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class listCorrida : Fragment() {
    private  var  _view : View? = null
    private val args by navArgs<listCorridaArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_listar_corrida, container, false)
        _view = view
        // Recyclerview
        val adapter = listCorridaAdapter(getUserIdInSession())
        val recyclerView = view.recyclerviewcorrida
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        getAndSetData(view)
        // UserViewModel

         /*   val item = adapter.setData(user,args.currentUser.id)
            if (item == 0)
                Toast.makeText(requireContext(), "Não existem corridas", Toast.LENGTH_LONG).show()
        })
*/
        view.floatingActionButtoncorrida.setOnClickListener {
            val action = listCorridaDirections.actionListarCorridaFragmentToAddCorridaFragment(args.currentUser)
            findNavController().navigate(action)
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_profile,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.update_profile){
            val action = listCorridaDirections.actionListarCorridaFragmentToUpdateFragment(args.currentUser)
            findNavController().navigate(action)
        }

        return super.onOptionsItemSelected(item)
    }









private fun getAndSetData(view: View) {

    view.llProgressBar.bringToFront()
    view.llProgressBar.visibility = View.VISIBLE


    val adapter = listCorridaAdapter(getUserIdInSession())

    val recyclerView = view.recyclerviewcorrida
    recyclerView.adapter = adapter
    recyclerView.layoutManager = LinearLayoutManager(requireContext())

    val request = ServiceBuilder.buildService(CorridasApi::class.java)
    val call = request.getCorrida(token = "Bearer ${getToken()}")

    call.enqueue(object : Callback<List<corridas>> {
        override fun onResponse(call: Call<List<corridas>>, response: Response<List<corridas>>) {

            llProgressBar.visibility = View.GONE

            if (response.isSuccessful) {
                val reports: List<corridas> = response.body()!!
                adapter.setData(reports,args.currentUser.id)
            } else {
                if (response.code() == 401) {
                    unauthorized(navigatonHandlder = {
                        findNavController().navigate(R.id.action_listFragment_to_loginFragment)
                    })
                } else {
                    somethingWentWrong()
                }
            }
        }

        override fun onFailure(call: Call<List<corridas>>, t: Throwable) {
            llProgressBar.visibility = View.GONE
            somethingWentWrong()
        }
    })
}

private fun logout() {
    val builder = AlertDialog.Builder(requireContext())
    builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
        val preferences = requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        preferences.edit().putString("token", null).apply()
        findNavController().navigate(R.id.action_listFragment_to_loginFragment)
    }
    builder.setNegativeButton(getString(R.string.no)) { _, _ -> }
    builder.setTitle(getString(R.string.logout))
    builder.setMessage(getString((R.string.logout_question)))
    builder.create().show()
}



}