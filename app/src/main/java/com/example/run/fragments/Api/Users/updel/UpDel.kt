package com.example.run.fragments.Api.Users.updel

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.run.R
import com.example.run.api.dto.UsersDto
import com.example.run.api.models.Users
import com.example.run.api.request.UsersApi
import com.example.run.api.retrofit.ServiceBuilder
import com.example.run.model.User
import com.example.run.utils.utils.Companion.getToken
import com.example.run.utils.utils.Companion.somethingWentWrong
import com.example.run.utils.utils.Companion.unauthorized
import com.example.run.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpDel : Fragment() {

    private val args by navArgs<UpDelArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)


        view.updateFirstName_et.setText(args.currentUser.firstName)
        view.updateLastName_et.setText(args.currentUser.lastName)
        view.updateAge_et.setText(args.currentUser.age.toString())

        view.update_btn.setOnClickListener {
            updateUsers()
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }



    private fun inputCheck(firstName: String, lastName: String, age: Int): Boolean {
        return (TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age.toString()))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }



    private fun updateUsers() {
        val firstName = updateFirstName_et.text.toString()
        val lastName = updateLastName_et.text.toString()
        val age = Integer.parseInt(updateAge_et.text.toString())
        if (inputCheck(firstName, lastName, age)) {
            Toast.makeText(
                requireContext(),
                getString(R.string.fill_all),
                Toast.LENGTH_LONG
            )
                .show()
        } else {
            val request = ServiceBuilder.buildService(UsersApi::class.java)
            val call = request.updateUsers(
                token = "Bearer ${getToken()}",
                id = args.currentUser.id,
                firstName = firstName,
                lastName = lastName,
                age = age
            )

            call.enqueue(object : Callback<UsersDto> {
                override fun onResponse(call: Call<UsersDto>, response: Response<UsersDto>) {
                    if (response.isSuccessful) {
                        val report: UsersDto = response.body()!!

                        if (report.status == "OK") {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.successfull_updated),
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
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
                                findNavController().navigate(R.id.action_updateFragment_to_loginFragment)
                            })
                        } else {
                            somethingWentWrong()
                        }
                    }
                }

                override fun onFailure(call: Call<UsersDto>, t: Throwable) {
                    somethingWentWrong()
                }
            })
        }
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->

            val request = ServiceBuilder.buildService(UsersApi::class.java)
            val call = request.deleteUsers(
                token = "Bearer ${getToken()}",
                id = args.currentUser.id
            )

            call.enqueue(object : Callback<UsersDto> {
                override fun onResponse(call: Call<UsersDto>, response: Response<UsersDto>) {
                    if (response.isSuccessful) {
                        val report: UsersDto = response.body()!!

                        if(report.status == "OK") {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.successfull_deleted),
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
                        }
                        else {
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

                        if(response.code() == 401){
                            unauthorized(navigatonHandlder = {
                                findNavController().navigate(R.id.action_updateFragment_to_loginFragment)
                            })
                        }
                        else {
                            somethingWentWrong()
                        }
                    }
                }

                override fun onFailure(call: Call<UsersDto>, t: Throwable) {
                    somethingWentWrong()
                }
            })
        }
        builder.setNegativeButton(getString(R.string.no)) { _, _ -> }
        builder.setTitle(getString(R.string.delete_users))
        builder.setMessage(getString(R.string.question_delete))
        builder.create().show()
    }
}
