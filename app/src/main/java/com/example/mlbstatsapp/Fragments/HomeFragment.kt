package com.example.mlbstatsapp

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MenuItemCompat
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mlbstatsapp.RecycleAdapters.FavoriteBattersAdapter
import com.example.mlbstatsapp.RecycleAdapters.FavoritePitcherAdapter
import com.example.mlbstatsapp.RecycleAdapters.FavoriteTeamsAdapter
import com.example.mlbstatsapp.database.Batter
import com.example.mlbstatsapp.database.Pitcher
import com.example.mlbstatsapp.database.Team

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

lateinit var homeViewModel:HomeViewModel
lateinit var favoritePlayersConstraintLayout:ConstraintLayout
lateinit var favoriteBattersConstraintLayout: ConstraintLayout
lateinit var favoritePitchersConstraintLayout: ConstraintLayout

lateinit var favoriteBatterRecycler:RecyclerView
lateinit var favoritePitcherRecycler: RecyclerView

lateinit var favoriteTeamRecycler: RecyclerView

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
public class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var sharedViewModel:HomePlayerSearchSharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    /**
     * We set the constraintLayouts, recyclerViews, searchEditText, searchButton
     * We set an onClickListener to the searchButton
     * We set out homeViewModel
     * We call a method to get favorite batters and pitchers
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View  = inflater.inflate(R.layout.fragment_home, container, false)
        favoritePlayersConstraintLayout = view.findViewById(R.id.favorite_players_constraint)
        favoriteBattersConstraintLayout = view.findViewById(R.id.favorite_batters_constraint)
        favoritePitchersConstraintLayout = view.findViewById(R.id.favorite_pitchers_constraint)

        favoriteBatterRecycler = view.findViewById(R.id.favorite_batters_recycler)
        favoritePitcherRecycler = view.findViewById(R.id.favorite_pitchers_recycler)

        favoriteTeamRecycler= view.findViewById(R.id.rvFavoriteTeams)

        homeViewModel = HomeViewModel(activity)
        getFavorites(view)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    /**
     * We get the favorite batters and pitchers from DB and put them in seperate lists
     * If both lists are empty we make favoritePlayersConstraintLayout not visible
     * If batter list is empty we make favoriteBattersConstraintLayout not visible
     * If pitcher list is empty we make favoritePitchersConstraintLayout not visible
     * For whichever lists are not empty we set the respective recycler views layoutManger and adapter
     */
    fun getFavorites(view:View){
        val batterList:List<Batter> = homeViewModel.getAllBatters()
        val pitcherList:List<Pitcher> = homeViewModel.getAllPitchers()
        val favoriteTeamsList: List<Team> = homeViewModel.getFavoriteTeams()
        Log.d(HomeFragment::class.java.simpleName, batterList.size.toString())
        Log.d(HomeFragment::class.java.simpleName, pitcherList.size.toString())

        if(batterList.isEmpty() && pitcherList.isEmpty()){
            favoritePlayersConstraintLayout.isVisible = false
            favoriteBatterRecycler.adapter = null
            favoritePitcherRecycler.adapter = null
        }
        if(batterList.isEmpty()){
            favoriteBattersConstraintLayout.isVisible = false
            favoriteBatterRecycler.adapter = null
        }
        if(pitcherList.isEmpty()){
            favoritePitchersConstraintLayout.isVisible = false
            favoritePitcherRecycler.adapter = null
        }
        if(batterList.isNotEmpty()){
            val layout:LinearLayoutManager = LinearLayoutManager(view.context)
            favoriteBatterRecycler.layoutManager = layout
            val batterAdapter = FavoriteBattersAdapter(batterList)
            favoriteBatterRecycler.adapter = batterAdapter
        }
        if(pitcherList.isNotEmpty()) {
            val layout:LinearLayoutManager = LinearLayoutManager(view.context)
            favoritePitcherRecycler.layoutManager = layout
            val pitcherAdapter = FavoritePitcherAdapter(pitcherList)
            favoritePitcherRecycler.adapter = pitcherAdapter
        }

        if(favoriteTeamsList.isNotEmpty()){
            val layout: LinearLayoutManager= LinearLayoutManager(view.context)
            favoriteTeamRecycler.layoutManager= layout
            val favoriteTeamsAdapter= FavoriteTeamsAdapter(favoriteTeamsList)
            favoriteTeamRecycler.adapter= favoriteTeamsAdapter
        }
    }
}