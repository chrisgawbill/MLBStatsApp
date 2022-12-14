package com.example.mlbstatsapp.Fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mlbstatsapp.R
import com.example.mlbstatsapp.RecycleAdapters.TeamListAdapter
import com.example.mlbstatsapp.TeamsListViewModel
import com.example.mlbstatsapp.database.Team

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TeamsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TeamsListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    lateinit var teamsListViewModel: TeamsListViewModel
    var teamsList= ArrayList<Team>()

    //  lateinit var teamNameText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

//        for(team in teamsList){
//               teamsNameList.add(team.location+" "+team.name)
//        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d(TeamsListFragment::class.java.simpleName, "Going to Inflate Layout")
        var view= inflater.inflate(R.layout.fragment_teams_list, container, false)
        teamsListViewModel = TeamsListViewModel(activity)
        teamsListViewModel.testTeamRetrieval()
        teamsList= teamsListViewModel.teamsInDB
        // teamNameText= view.findViewById(R.id.teamNameText)
        Log.d(TeamsListFragment::class.java.simpleName, "Inflated Layout")
        var teamsListAdapter =  TeamListAdapter(teamsList, activity)
        Log.d(TeamsListFragment::class.java.simpleName, "Created Adapter")
        var recyclerView: RecyclerView= view.findViewById(R.id.rvTeamNames)
        Log.d(TeamsListFragment::class.java.simpleName, "Found Recycler View")


        recyclerView.layoutManager= LinearLayoutManager(view.context);
        recyclerView.adapter= teamsListAdapter;
        Log.d(TeamsListFragment::class.java.simpleName, "Attatched Team List Adapter")

        return view;
    }










    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TeamsListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TeamsListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }
}