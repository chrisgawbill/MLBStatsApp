package com.example.mlbstatsapp.Fragments

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mlbstatsapp.R
import com.example.mlbstatsapp.RecycleAdapters.TeamsListAdapter
import com.example.mlbstatsapp.TeamsListViewModel
import com.example.mlbstatsapp.database.Team
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

var teamsListViewModel: TeamsListViewModel= TeamsListViewModel()


class IndividualTeamFragment(): Fragment() {



    private var param1: String? = null
    private var param2: String? = null
    private lateinit var locationText: TextView
    private lateinit var nameText: TextView
    private lateinit var leagueText: TextView
    private lateinit var divisionText: TextView
    private lateinit var recordText: TextView
    private lateinit var streakText: TextView
    private lateinit var gamesBackText: TextView
    private lateinit var lastTenText: TextView
    private lateinit var pctWinText: TextView

    private lateinit var location: String
    private lateinit var name: String
    private lateinit var league: String
    private lateinit var division: String
    private lateinit var wins: String
    private lateinit var losses: String
    private lateinit var gamesBack: String
    private lateinit var streak: String
    private lateinit var lastTen: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            location= arguments?.getString("location").toString()
            name= arguments?.getString("name").toString()
            league= arguments?.getString("league").toString()
            division= arguments?.getString("division").toString()
            wins= arguments?.getString("wins").toString()
            losses= arguments?.getString("losses").toString()
            streak= arguments?.getString("streak").toString()
            gamesBack= arguments?.getString("games_back").toString()
            lastTen= arguments?.getString("last_ten").toString()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view= inflater.inflate(R.layout.fragment_individual_team, container, false)


       // Log.i("Team Name Passed: ", this.arguments?.getString("name"))




        locationText= view.findViewById(R.id.textViewLocation)
       locationText.setText(location)

        nameText= view.findViewById(R.id.textViewName)
        nameText.setText(name)

        leagueText= view.findViewById(R.id.textViewLeague)
        leagueText.setText(league)

        divisionText= view.findViewById(R.id.textViewDivision)
        divisionText.setText(division)

        recordText = view.findViewById(R.id.textViewRecord)
        recordText.setText(wins+"-"+losses)

        pctWinText= view.findViewById(R.id.textViewPct)

        val numWins= wins.toInt()
        val numLosses= losses.toInt()
        val percentage=  numWins.toBigDecimal().divide((numLosses+numWins).toBigDecimal(), MathContext(3, RoundingMode.HALF_UP))
        var percentageText= percentage.toString().substring(1)

        if(percentageText.length==2){
            percentageText+= "00"
        }
        if(percentageText.length==3){
            percentageText+="0"
        }


        pctWinText.setText(percentageText)

        streakText= view.findViewById(R.id.textViewStreak)
        streakText.setText(streak)

        gamesBackText= view.findViewById(R.id.textViewGamesBack)
        gamesBackText.setText(gamesBack)

       lastTenText= view.findViewById(R.id.textViewLastTen)
      lastTenText.setText(lastTen)

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


