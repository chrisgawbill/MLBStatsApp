package com.example.mlbstatsapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mlbstatsapp.databinding.FragmentPlayerSearchResultsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlayerSearchResultsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayerSearchResultsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var sharedViewModel:HomePlayerSearchSharedViewModel
    lateinit var searchTerm:String

    lateinit var playerSearchResultsRecycler:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var bind:FragmentPlayerSearchResultsBinding = FragmentPlayerSearchResultsBinding.inflate(inflater, container, false)
        var view = bind.root

        val viewModel = makeApiCall(view)
        bind.setVariable(BR.viewModel, viewModel)
        bind.executePendingBindings()

        playerSearchResultsRecycler = view.findViewById(R.id.playerSearchResultsRecyclerView)
        playerSearchResultsRecycler.apply {
            layoutManager = LinearLayoutManager(this.context)
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PlayerSearchResultsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlayerSearchResultsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun makeApiCall(view:View):HomeViewModel{
        var homeView:HomeViewModel = HomeViewModel()
        sharedViewModel = activity?.run {
            ViewModelProviders.of(this).get(HomePlayerSearchSharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        sharedViewModel.playerSearchTerm.observe(viewLifecycleOwner, Observer {
             searchTerm = sharedViewModel.playerSearchTerm.value.toString()
             homeView.getPlayerSearchResultsDataObserver().observe(viewLifecycleOwner,Observer<PlayerList>{
                if(it != null){
                    Log.d(PlayerSearchResultsFragment::class.java.simpleName, it.row.toString())
                    homeView.setAdapterData(it.row as ArrayList<PlayerApiModel>)
                }else{
                    Log.d(PlayerSearchResultsFragment::class.java.simpleName, "SOMETHING WENT WRONG")
                    Toast.makeText(view.context, "Error something went wrong with data", Toast.LENGTH_SHORT).show()
                }
            })
            homeView.getPlayerSearchList(searchTerm)
        })

        return homeView
    }
}