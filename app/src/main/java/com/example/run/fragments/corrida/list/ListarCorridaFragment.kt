package com.example.run.fragments.corrida.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.run.R
import com.example.run.fragments.users.update.list.ListAdapter
import com.example.run.viewmodel.CorridaViewModel
import com.example.run.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.fragment_listar_corrida.view.*

/*class ListarCorridaFragment : Fragment() {
    private lateinit var mCorridaViewModel: CorridaViewModel
    private val args by navArgs<ListarCorridaFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_listar_corrida, container, false)

        // Recyclerview
        val adapter = ListAdapterCorrida()
        val recyclerView = view.recyclerviewcorrida
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        mCorridaViewModel = ViewModelProvider(this).get(CorridaViewModel::class.java)
        mCorridaViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            val item = adapter.setData(user,args.currentUser.id)
            if (item == 0)
            Toast.makeText(requireContext(), "Não existem corridas", Toast.LENGTH_LONG).show()
        })

  view.floatingActionButtoncorrida.setOnClickListener {
            //val action = ListarCorridaFragmentDirections.actionListarCorridaFragmentToAddCorridaFragment(args.currentUser)
            //findNavController().navigate(action)
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
            val action = ListarCorridaFragmentDirections.actionListarCorridaFragmentToUpdateFragment(args.currentUser)
            findNavController().navigate(action)
        }

        return super.onOptionsItemSelected(item)
    }





}*/