package com.example.run.fragments.corrida.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.run.R
import com.example.run.model.Corrida
import kotlinx.android.synthetic.main.custom_row_corrida.view.*

class ListAdapterCorrida: RecyclerView.Adapter<ListAdapterCorrida.MyViewHolder>() {
    private var corridaList = emptyList<Corrida>()
    private var corridaList2 : MutableList<Corrida> = ArrayList()


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapterCorrida.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row_corrida, parent, false))
    }

    override fun getItemCount(): Int {
        return corridaList2.size
    }

    override fun onBindViewHolder(holder: ListAdapterCorrida.MyViewHolder, position: Int) {

            val currentItem = corridaList2[position]
            holder.itemView.tv_km.text = currentItem.km.toString()
            holder.itemView.txt_data.text = currentItem.data
            holder.itemView.txt_hinicio.text = currentItem.hora_inicio
            holder.itemView.txt_hfim.text = currentItem.hora_fim




        /*holder.itemView.rowLayoutcorrida.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
            }
        */
    }


    fun setData(corrida: List<Corrida>,user: Int):Int{
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