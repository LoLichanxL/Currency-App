package com.example.news_details

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.news_details.databinding.FragmentNewsDetailsBinding
import com.example.utils.navigation.NavCommand
import com.example.utils.navigation.NavCommands
import com.example.utils.navigation.navigate

class NewsDetailsFragment : Fragment() {
    private lateinit var binding:FragmentNewsDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        Log.d("Args", arguments?.get("uri").toString())
        subscribeOnClickListeners()
        return binding.root
    }

    private fun subscribeOnClickListeners(){
        binding.arrowBack.setOnClickListener(View.OnClickListener {
            navigate(NavCommand(NavCommands.DeepLink(Uri.parse("currencyapp://main"), true, true)))
        })
    }
}