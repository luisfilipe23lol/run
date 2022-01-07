package com.example.run.fragments.Api.Users.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.run.R
import com.example.run.api.models.Users

import kotlinx.android.synthetic.main.custom_row.view.*

class ListUsersAdapterApi(userIdInSession: String?): RecyclerView.Adapter<ListUsersAdapterApi.MyViewHolder>() {
    private var userList = emptyList<Users>()
    private  val _userIdInSession = userIdInSession
    private  var  _ctx : Context? = null

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        _ctx=parent.context
        return ListUsersAdapterApi.MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    fun setData(user: List<Users>){
        this.userList = user
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListUsersAdapterApi.MyViewHolder, position: Int) {

        val currentItem = userList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.firstName_txt.text = currentItem.firstName
        holder.itemView.lastName_txt.text = currentItem.lastName
        holder.itemView.age_txt.text = currentItem.age.toString()

        /*holder.itemView.rowLayout.setOnClickListener {
             val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
             holder.itemView.findNavController().navigate(action)
         }*/

        holder.itemView.rowLayout.setOnClickListener {
            if(_userIdInSession == currentItem.users_id.toString()) {
                val action =
                   ListUsersFragmentApiDirections.actionListFragmentToListarCorridaFragment(currentItem)
                holder.itemView.findNavController().navigate(action)
            }
            else {
                Toast.makeText(_ctx,R.string.ony_edit_your_reports, Toast.LENGTH_LONG).show()
            }
        }
    }
    }

