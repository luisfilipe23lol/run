package com.example.run.fragments.Api.corrida.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.run.R
import com.example.run.api.models.corridas
import com.example.run.fragments.Api.Users.list.ListUsersFragmentApiDirections
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.custom_row_corrida.view.*

class listCorridaAdapter(userIdInSession: String?): RecyclerView.Adapter<listCorridaAdapter.MyViewHolder>() {
    private var corridaList = emptyList<corridas>()
    private var corridaList2 : MutableList<corridas> = ArrayList()
    private  val _userIdInSession = userIdInSession
    private  var  _ctx : Context? = null

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listCorridaAdapter.MyViewHolder {
        _ctx=parent.context
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row_corrida, parent, false))
    }

    override fun getItemCount(): Int {
        return corridaList2.size
    }

    override fun onBindViewHolder(holder: listCorridaAdapter.MyViewHolder, position: Int) {

        val currentItem = corridaList2[position]
        holder.itemView.tv_km.text = currentItem.km.toString()
        holder.itemView.txt_data.text = currentItem.data
        holder.itemView.txt_hinicio.text = currentItem.hora_inicio
        holder.itemView.txt_hfim.text = currentItem.hora_fim


       /* holder.itemView.rowLayout.setOnClickListener {
            if(_userIdInSession == currentItem.users_id.toString()) {
                val action = listc.actionListFragmentToUpdateFragment(currentItem)
                holder.itemView.findNavController().navigate(action)
            }
            else {
                Toast.makeText(_ctx,R.string.ony_edit_your_reports, Toast.LENGTH_LONG).show()
            }
        }*/

        /*holder.itemView.rowLayoutcorrida.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
            }
        */
    }


    fun setData(corrida: List<corridas>, user: Int):Int{
        var item = 0
        this.corridaList = corrida
        notifyDataSetChanged()
        this.corridaList.forEach{
            if (it.id_user==user){
                this.corridaList2.add(it)
                item++
            }
        }
        return  item
    }

}