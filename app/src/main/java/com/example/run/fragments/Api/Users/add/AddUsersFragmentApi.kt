package com.example.run.fragments.Api.Users.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.run.R
import com.example.run.api.dto.UsersDto
import com.example.run.api.models.Users
import com.example.run.api.request.UsersApi
import com.example.run.api.retrofit.ServiceBuilder
import com.example.run.model.User
import com.example.run.utils.utils.Companion.getToken
import com.example.run.utils.utils.Companion.getUserIdInSession
import com.example.run.utils.utils.Companion.hideKeyboard
import com.example.run.utils.utils.Companion.somethingWentWrong
import com.example.run.utils.utils.Companion.unauthorized
import com.example.run.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddUsersFragmentApi : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)



        view.add_btn.setOnClickListener {
            hideKeyboard()
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val firstName = addFirstName_et.text.toString()
        val lastName = addLastName_et.text.toString()
        val age = addAge_et.text.toString().toInt()


           addUsers(firstName,lastName,age)
    }

    private fun inputCheck(firstName: String, lastName: String, age: Int): Boolean {
        return (TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age.toString()))
    }

    private fun addUsers(firstName: String, lastName: String, age: Int) {
        if (inputCheck(firstName, lastName, age)) {
            Toast.makeText(
                requireContext(),
                getString(R.string.fill_all),
                Toast.LENGTH_LONG
            )
                .show()
        } else {
            llProgressBar.bringToFront()
            llProgressBar.visibility = View.VISIBLE

            val request = ServiceBuilder.buildService(UsersApi::class.java)
            val call = request.createUsers(
                token = "Bearer ${getToken()}",
                users_id = getUserIdInSession(),
                firstName = firstName,
                lastName = lastName,
                age = age

            )

            call.enqueue(object : Callback<UsersDto> {
                override fun onResponse(call: Call<UsersDto>, response: Response<UsersDto>) {
                    llProgressBar.visibility = View.GONE

                    if (response.isSuccessful) {
                        val report: UsersDto = response.body()!!

                        if (report.status == "OK") {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.successfull_added),
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(R.id.action_addFragment_to_listFragment)
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
                                findNavController().navigate(R.id.action_addFragment_to_loginFragment)
                            })
                        } else {
                            somethingWentWrong()
                        }
                    }
                }

                override fun onFailure(call: Call<UsersDto>, t: Throwable) {
                    llProgressBar.visibility = View.GONE
                    somethingWentWrong()
                }
            })
        }
    }
}