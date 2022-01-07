package com.example.run.fragments.Api.Users.list

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.run.LayoutActivity2
import com.example.run.R
import com.example.run.api.models.Users
import com.example.run.api.request.UsersApi
import com.example.run.api.retrofit.ServiceBuilder
import com.example.run.utils.utils.Companion.getToken
import com.example.run.utils.utils.Companion.getUserIdInSession
import com.example.run.utils.utils.Companion.somethingWentWrong
import com.example.run.utils.utils.Companion.unauthorized
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListUsersFragmentApi : Fragment() {
    private  var  _view : View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        _view = view
        // Recyclerview
        val adapter = ListUsersAdapterApi(getUserIdInSession())
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        getAndSetData(view)

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        inflater.inflate(R.menu.user_login, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllUsers()
        }
        if (item.itemId == R.id.user_login) {
            openlogin()
        }

            if (item.itemId == R.id.menu_logout) {
                logout()
            }

            if(item.itemId == R.id.menu_refresh){
                _view?.let { getAndSetData(it) }
            }

        if(item.itemId == R.id.menu_maps){
            val intent = Intent(requireContext(), LayoutActivity2::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)

    }

    private fun openlogin() {
        findNavController().navigate(R.id.action_listFragment_to_loginFragment)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->

            Toast.makeText(
                requireContext(),
                "Successfully removed everything",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }



    private fun getAndSetData(view: View) {

        view.llProgressBar.bringToFront()
        view.llProgressBar.visibility = View.VISIBLE


        val adapter = ListUsersAdapterApi(getUserIdInSession())

        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val request = ServiceBuilder.buildService(UsersApi::class.java)
        val call = request.getUsers(token = "Bearer ${getToken()}")

        call.enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {

                llProgressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    val reports: List<Users> = response.body()!!
                    adapter.setData(reports)
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

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
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
