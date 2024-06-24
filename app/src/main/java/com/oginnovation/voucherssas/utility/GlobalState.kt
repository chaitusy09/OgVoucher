package com.kuwait.pravastelugu.utility

import android.app.Dialog
import android.content.Context
import android.util.Base64
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.org.evcharge.R


class GlobalState {
    private var gs: GlobalState? = null
     var ISMEM: String = "ISMEM"
    var userName: String = "userName"
    var password: String = "password"

     var LOGINRESPONSE: String = "LOGINRESPONSE"
     var ADDFAMILYMEMBER: String = "ADDFAMILYMEMBER"
     var showAdd: String = "SHOWADD"
    var TOKEN: String = "token"

    fun GetGlobalState(): GlobalState? {
       if (gs == null) {
           gs = GlobalState()
           return gs
        } else {
           return gs
        }
    }
    fun makeFullScreen(activity: AppCompatActivity) {
        val windowInsetsController = WindowCompat.getInsetsController(activity.window, activity.window.decorView)
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
    fun showToast(message: String,context: Context) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
    fun storeSharedPreferences(context: Context, key: String, value: Any) {
        val sharedPreferences = context.getSharedPreferences("my_shared_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        when (value) {
            is Int -> editor.putInt(key, value)
            is Long -> editor.putLong(key, value)
            is String -> editor.putString(key, value)
            is Boolean -> editor.putBoolean(key, value)
        }

        editor.apply()
    }

    fun retrieveSharedPreferences(context: Context, key: String, defaultValue: Any): Comparable<*>? {
        val sharedPreferences = context.getSharedPreferences("my_shared_preferences", Context.MODE_PRIVATE)

        return when (defaultValue) {
            is Int -> sharedPreferences.getInt(key, defaultValue)
            is Long -> sharedPreferences.getLong(key, defaultValue )
            is String -> sharedPreferences.getString(key, defaultValue )
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue )
            else -> throw IllegalArgumentException("Unsupported type: ${defaultValue.javaClass.name}")
        }
    }

    fun divideString(string: String, delimiter: Char): Pair<String, String> {
        val index = string.indexOf(delimiter)
        return if (index == -1) {
            Pair(string, "")
        } else {
            Pair(string.substring(0, index), string.substring(index + 1))
        }
    }

    fun getPayloadFromJwt(jwt: String): String {
        // Split the JWT token into three parts.
        val parts = jwt.split(".")

        // The payload is the second part of the JWT token.
        val payload = parts[1]

        // Decode the payload from Base64.
        val decodedPayload = Base64.decode(payload, Base64.URL_SAFE)

        // Return the decoded payload as a string.
        return String(decodedPayload)
    }
    fun showDialog(c: Context?): Dialog? {
        val dialog = Dialog(c!!, R.style.blackDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.loading)
        dialog.setCancelable(false)
        dialog.show()
        return dialog
    }
}