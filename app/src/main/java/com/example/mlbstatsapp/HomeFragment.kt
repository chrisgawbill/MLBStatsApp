package com.example.mlbstatsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var playerSearchBtn:Button
    lateinit var playerSearchText:EditText
    lateinit var sharedViewModel:HomePlayerSearchSharedViewModel

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
        var view:View  = inflater.inflate(R.layout.fragment_home, container, false)
        playerSearchBtn = view.findViewById(R.id.searchPlayerButton)
        playerSearchText = view.findViewById(R.id.searchPlayerText)
        playerSearchBtn.setOnClickListener(View.OnClickListener {
            var playerSearchClickParam = playerSearchText.text.toString()
            playerSearchClick(playerSearchClickParam, view)
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
    fun playerSearchClick(playerSearchParam:String, view:View){

        sharedViewModel = activity?.run{
            ViewModelProviders.of(this).get(HomePlayerSearchSharedViewModel::class.java)


        }?: throw Exception("Invalid Activity")

        sharedViewModel.playerSearchTerm.value = playerSearchParam

        // Just for testing purposes to verify the button is execution this function.   Comment out before deployment
        val toast= Toast.makeText(context, "This also works", Toast.LENGTH_SHORT)
        toast.show()


        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_playerSearchResultsFragment);


    }
}