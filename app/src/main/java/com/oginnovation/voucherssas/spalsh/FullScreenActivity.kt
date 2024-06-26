package com.oginnovation.voucherssas.spalsh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.oginnovation.voucherssas.utility.GlobalState


open class FullScreenActivity : AppCompatActivity() {
    lateinit var gs : GlobalState
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gs = GlobalState().GetGlobalState()!!
        gs.makeFullScreen(this@FullScreenActivity)
    }
    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}