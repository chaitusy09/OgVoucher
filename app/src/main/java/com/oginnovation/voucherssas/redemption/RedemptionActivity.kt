package com.oginnovation.voucherssas.redemption

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import com.oginnovation.voucherssas.R

class RedemptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redemption)
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        val backButton = findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            // Handle back button click
            finish()  // Example: Close activity
        }
    }
}