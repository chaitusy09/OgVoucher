package com.oginnovation.voucherssas.redemtionhistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oginnovation.voucherssas.R
import com.oginnovation.voucherssas.vouchers.model.ChargerStation
import com.oginnovation.voucherssas.vouchers.view.CustomAdapter

class RedemtionHistoryActivity : AppCompatActivity() {
    lateinit var recyclerHistory: RecyclerView
    private lateinit var adapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redemtion_history)
        recyclerHistory = findViewById<RecyclerView>(R.id.recyclerReedemHistory)
        recyclerHistory.layoutManager = LinearLayoutManager(this)
        //if(data!=null) {
        var data = ArrayList<ChargerStation>()
        adapter =
            CustomAdapter(data, this@RedemtionHistoryActivity)  // Setting the Adapter with the recyclerview
        recyclerHistory.adapter = adapter
    }
}