package com.oginnovation.voucherssas


import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts


import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oginnovation.voucherssas.redemtionhistory.RedemtionHistoryActivity
import com.oginnovation.voucherssas.scanner.VoucherScanActivity
import com.oginnovation.voucherssas.vouchers.model.ChargerStation
import com.oginnovation.voucherssas.vouchers.view.BootomSheetDialogueReedemCode
import com.oginnovation.voucherssas.vouchers.view.BottomSheetFragment
import com.oginnovation.voucherssas.vouchers.view.CustomAdapter


class MainActivity : AppCompatActivity() {
    lateinit var recyclerHistory: RecyclerView
    var txtViewAll:TextView?=null
    private lateinit var adapter: CustomAdapter
    lateinit var launcher: ActivityResultLauncher<String>
    var btnScan: RelativeLayout? = null
    var btnAddCode: LinearLayout? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerHistory = findViewById(R.id.recyclerReedemHistory)
        btnScan = findViewById(R.id.btnScan)
        btnAddCode = findViewById(R.id.linearButtonAdsdCode)
        txtViewAll=findViewById(R.id.txtViewAll)


        recyclerHistory.layoutManager = LinearLayoutManager(this)
        //if(data!=null) {
        var data = ArrayList<ChargerStation>()
        adapter =
            CustomAdapter(data, this@MainActivity)  // Setting the Adapter with the recyclerview
        recyclerHistory.adapter = adapter
        //}

        btnScan?.setOnClickListener(View.OnClickListener {
            requestCameraPermission()
        })
        txtViewAll?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, RedemtionHistoryActivity::class.java)
            startActivity(intent)
        })
        btnAddCode?.setOnClickListener(View.OnClickListener {
            val bottomSheet = BootomSheetDialogueReedemCode()
            bottomSheet.show(supportFragmentManager, "bottomSheetTag")
        })

        launcher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    launchForScan()
                    // Permission granted, open camera or perform camera related tasks
                } else {
                    // Explain why permission is needed and ask user to grant it again
                }
            }

    }

    fun requestCameraPermission() {
        launcher.launch(Manifest.permission.CAMERA)
    }
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data?.getStringExtra("result")
            Toast.makeText(this, "Scan result: $data", Toast.LENGTH_LONG).show()
            val bottomSheet = BottomSheetFragment()
            bottomSheet.show(supportFragmentManager, "bottomSheetTag")
            // Handle the result here
        }
    }

    fun launchForScan() {
        val intent = Intent(this, VoucherScanActivity::class.java)
        resultLauncher.launch(intent)
    }

}

