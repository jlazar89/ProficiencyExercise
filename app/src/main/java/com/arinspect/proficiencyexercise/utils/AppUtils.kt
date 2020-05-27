package com.arinspect.proficiencyexercise.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

/**
 * Created Date: 27-05-2020
 * Purpose: This object will contains all the utility methods needed to the specific app.
 */
object AppUtils {

    /**
     * Function to check network connection.
     */
    fun hasNetwork(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return (activeNetwork != null && activeNetwork.isConnected)
    }
}