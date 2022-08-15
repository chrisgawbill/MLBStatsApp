package com.example.mlbstatsapp.Fragments

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Binder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.mlbstatsapp.*
import com.example.mlbstatsapp.ApiModels.PlayerHittingStats
import com.example.mlbstatsapp.ApiModels.PlayerPitchingStat
import com.example.mlbstatsapp.database.Batter
import com.example.mlbstatsapp.database.Pitcher
import com.example.mlbstatsapp.database.Player
import com.example.mlbstatsapp.databinding.FragmentIndividualPlayerBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
var individualPlayerViewModel: IndividualPlayerViewModel = IndividualPlayerViewModel()

/**
 * A simple [Fragment] subclass.
 * Use the [IndividualPlayer.newInstance] factory method to
 * create an instance of this fragment.
 */
class IndividualPlayer : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var hittingStatsLayout:ConstraintLayout
    lateinit var pitchingStatsLayout: ConstraintLayout

    lateinit var favoriteIcon:ImageButton
    lateinit var pitcher: Pitcher
    lateinit var batter: Batter

    var playerId:Int = 0
    var playerPosition:String = ""

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
        var bind: com.example.mlbstatsapp.databinding.FragmentIndividualPlayerBinding = com.example.mlbstatsapp.databinding.FragmentIndividualPlayerBinding.inflate(inflater, container, false)
        var view = bind.root
        hittingStatsLayout = view.findViewById(R.id.hittingStatConstraintLayout)
        pitchingStatsLayout = view.findViewById(R.id.pitchingStatConstraintLayout)

        val safeArgs: IndividualPlayerArgs by navArgs()
        playerId = safeArgs.playerId
        playerPosition = safeArgs.playerPosition
        val playerFirstName = safeArgs.playerFirstName
        val playerLastName = safeArgs.playerLastName
        val playerHeight = safeArgs.playerHeight
        val playerWeight = safeArgs.playerWeight
        val playerCurrentTeam = safeArgs.playerCurrentTeam
        val playerHometown = safeArgs.playerHometown
        val playerBats = safeArgs.playerBats
        val playerThrows = safeArgs.playerThrows
        if(playerPosition == "P"){

        }

        individualPlayerViewModel.setGeneralPlayerInfo(playerPosition, playerFirstName,playerLastName,playerHeight, playerWeight,
        playerCurrentTeam,playerThrows,playerBats, playerHometown)

        Log.d(IndividualPlayer::class.java.simpleName, playerId.toString())
        Log.d(IndividualPlayer::class.java.simpleName, playerPosition)
        makeStatsApiCall(playerId,playerPosition, bind, view, safeArgs)

        favoriteIcon = view.findViewById(R.id.favoritePlayerImageButton)
        setFavoriteImageButton(playerId, playerPosition, view)
        favoriteIcon.setOnClickListener(View.OnClickListener {
            favoriteIconButtonClick()
        })

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment IndividualPlayer.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IndividualPlayer().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun makeStatsApiCall(playerIdParam:Int, playerPositionParam:String, bindParam: com.example.mlbstatsapp.databinding.FragmentIndividualPlayerBinding, viewParam:View, safeArgsParam:IndividualPlayerArgs){
        if(playerPositionParam == "P"){
            makePitchingStatsCall(playerIdParam, bindParam, viewParam)
        }else{
            makeHittingStatsCall(playerIdParam, bindParam, viewParam)
        }
    }
    fun makeHittingStatsCall(playerIdParam:Int, bindParam: FragmentIndividualPlayerBinding, viewParam:View){
        individualPlayerViewModel.getPlayerHittingStatsData().observe(viewLifecycleOwner, Observer<PlayerHittingStats>{
                if(it != null){
                    Log.d(PlayerSearchResultsFragment::class.java.simpleName, (it as PlayerHittingStats).toString())
                    bindParam.setVariable(BR.playerStats, individualPlayerViewModel)
                    hittingStatsLayout.isVisible = true
                    pitchingStatsLayout.isVisible = false
                }else{
                    Log.d(PlayerSearchResultsFragment::class.java.simpleName, "SOMETHING WENT WRONG")
                    Toast.makeText(viewParam.context, "Error something went wrong with data", Toast.LENGTH_SHORT).show()
                }
            })
            individualPlayerViewModel.getPlayerHittingStats(playerIdParam)
        }
    fun makePitchingStatsCall(playerIdParam:Int, bindParam:FragmentIndividualPlayerBinding, viewParam:View){
        individualPlayerViewModel.getPlayerPitchingStatsData().observe(viewLifecycleOwner, Observer<PlayerPitchingStat>{
            if(it != null){
                Log.d(PlayerSearchResultsFragment::class.java.simpleName, (it as PlayerPitchingStat).toString())
                bindParam.setVariable(BR.playerStats, individualPlayerViewModel)
                hittingStatsLayout.isVisible = false
                pitchingStatsLayout.isVisible = true
            }else{
                Log.d(PlayerSearchResultsFragment::class.java.simpleName, "SOMETHING WENT WRONG")
                Toast.makeText(viewParam.context, "Error something went wrong with data", Toast.LENGTH_LONG).show()
                Navigation.findNavController(viewParam).navigate(R.id.action_individualPlayer_to_homeFragment)
            }
        })
        individualPlayerViewModel.getPlayerPitchingStats(playerIdParam)
    }
    fun setFavoriteImageButton(playerIdParam:Int, playerPositionParam: String, viewParam:View){
        var pitcherFavorite:Pitcher? = null
        var batterFavorite:Batter? = null
        Log.d(IndividualPlayer::class.java.simpleName, playerIdParam.toString())
        val db = LoadData.getInstance(activity?.applicationContext ?: activity?.applicationContext)
        if(playerPositionParam == "P"){
            pitcherFavorite = db.getPitcher(playerIdParam.toString())
        }else{
            batterFavorite = db.getBatter(playerIdParam.toString())
        }

        if(pitcherFavorite == null && batterFavorite == null){
            Log.d(IndividualPlayer::class.java.simpleName, "Player is not a favorite")
            favoriteIcon.setImageResource(R.drawable.non_favorited_player_icon)
            favoriteIcon.setTag(R.id.favoritePlayerImageButton,"Not Favorite")
        }else{
            Log.d(IndividualPlayer::class.java.simpleName, "Player was a favorite")
            favoriteIcon.setImageResource(R.drawable.favorited_player_icon)
            favoriteIcon.setTag(R.id.favoritePlayerImageButton, "Favorite")
        }
    }
    fun favoriteIconButtonClick(){
        var tag = favoriteIcon.getTag(R.id.favoritePlayerImageButton)
        if(tag.equals("Favorite")){
            favoriteIcon.setImageResource(R.drawable.non_favorited_player_icon)
            favoriteIcon.setTag(R.id.favoritePlayerImageButton, "Not Favorited")
            deleteFavoriteFromDB()
        }else{
            favoriteIcon.setImageResource(R.drawable.favorited_player_icon)
            favoriteIcon.setTag(R.id.favoritePlayerImageButton, "Favorite")
            insertFavoriteIntoDB()
        }
    }
    fun insertFavoriteIntoDB(){
       individualPlayerViewModel.insertFavoriteIntoDb(activity, playerId.toString());
    }
    fun deleteFavoriteFromDB(){
        individualPlayerViewModel.deleteFavoritePlayerFromDb(activity, playerId.toString());
    }
}