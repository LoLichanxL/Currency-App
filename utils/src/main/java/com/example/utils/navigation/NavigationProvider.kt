package com.example.utils.navigation

import com.example.utils.navigation.NavCommand

interface NavigationProvider {
    fun launch(navCommand: NavCommand)

}