package com.example.currencyapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.utils.navigation.NavCommand
import com.example.utils.navigation.NavCommands
import com.example.utils.navigation.NavigationProvider

class MainActivity : AppCompatActivity(), NavigationProvider{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun launch(navCommand: NavCommand) {
        when(val target = navCommand.target){
            is NavCommands.Browser ->{
                openBrowser(target.url)
            }
            is NavCommands.DeepLink ->{
                openDeepLink(target.url, target.isModal, target.isSingleTop)
            }
        }
    }

    private fun openBrowser(url: String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        browserIntent.setPackage("com.android.chrome")
        try {
            this.startActivity(browserIntent)
        } catch (ex: ActivityNotFoundException) {
            browserIntent.setPackage(null)
            this.startActivity(browserIntent)
        }
    }

    private fun openDeepLink(url: Uri, isModal:Boolean, isSingleTop:Boolean){
        val navOptions = if (isModal) {
            NavOptions.Builder()
                .setLaunchSingleTop(isSingleTop)
                .setPopUpTo(if (isSingleTop) R.id.main_nav_graph else -1, inclusive = isSingleTop)
                .build()
        } else {
            NavOptions.Builder()
                .setLaunchSingleTop(isSingleTop)
                .setPopUpTo(if (isSingleTop) R.id.main_nav_graph else -1, inclusive = isSingleTop)
                .build()
        }
        findNavController(R.id.nav_host_fragment).navigate(url, navOptions)
    }
}