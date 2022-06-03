package com.example.currencyapp

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.example.utils.navigation.NavCommand
import com.example.utils.navigation.NavCommands
import com.example.utils.navigation.navigate

class SplashFragment : Fragment(R.layout.fragment_splash) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigate(NavCommand(NavCommands.DeepLink(url = Uri.parse("currencyapp://main"), true, true)))
    }

}