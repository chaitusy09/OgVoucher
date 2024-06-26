package com.oginnovation.voucherssas


import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels


import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.kuwait.pravastelugu.viewmodel.CommonViewModel
import com.kuwait.pravastelugu.viewmodel.ViewModelFactory
import com.oginnovation.voucherssas.redemtionhistory.RedemtionHistoryActivity
import com.oginnovation.voucherssas.scanner.VoucherScanActivity
import com.oginnovation.voucherssas.spalsh.FullScreenActivity
import com.oginnovation.voucherssas.token.TokenBody
import com.oginnovation.voucherssas.utility.Resource
import com.oginnovation.voucherssas.vouchers.model.ChargerStation
import com.oginnovation.voucherssas.vouchers.view.BootomSheetDialogueReedemCode
import com.oginnovation.voucherssas.vouchers.view.BottomSheetFragment
import com.oginnovation.voucherssas.vouchers.view.CustomAdapter


class MainActivity : FullScreenActivity() {
    lateinit var recyclerHistory: RecyclerView
    var txtViewAll: TextView? = null
    private lateinit var adapter: CustomAdapter
    lateinit var launcher: ActivityResultLauncher<String>
    var btnScan: RelativeLayout? = null
    var btnAddCode: LinearLayout? = null
    var dialog: Dialog? = null
    lateinit var code: String
    lateinit var state: String

    private val commonViewModel: CommonViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerHistory = findViewById(R.id.recyclerReedemHistory)
        btnScan = findViewById(R.id.btnScan)
        btnAddCode = findViewById(R.id.linearButtonAdsdCode)
        txtViewAll = findViewById(R.id.txtViewAll)
        code= intent.getStringExtra(gs.code).toString()
        state= intent.getStringExtra(gs.state).toString()



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
        if((gs.retrieveSharedPreferences(this@MainActivity,gs.TOKEN,""))!!.equals("")){
            onTokenGenerate()
        }

    }


    fun requestCameraPermission() {
        launcher.launch(Manifest.permission.CAMERA)
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
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

    private fun onTokenGenerate() {
        //val email = edtEmail?.text.toString()
        //val password = edtPass?.text.toString()

        val tokenBody = TokenBody(code, state, gs.redirectURL)
        commonViewModel.generateToken(tokenBody, this)
        // Observe the login data
        commonViewModel.generateTokendata.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        hideProgressBar()
                        response.data?.let { tokenResponse ->
                            if (tokenResponse != null) {

                                //val logindata = Gson().toJson(tokenResponse)
                                gs.storeSharedPreferences(this, gs.TOKEN, tokenResponse.data.accessToken)


                                //var vald: String = gs.getPayloadFromJwt(loginResponse.Token)
                                // gs.storeSharedPreferences(this, gs.TOKEN, loginResponse.Token)
                                /* val datak: LoginResponse = Gson().fromJson(
                                     vald.toString(),
                                     LoginResponse::class.java
                                 )*/
                                /*  val containsCustomer = datak.roles.contains("Customer")
                                 if(containsCustomer){
                                     value ="member"
                                 }else {
                                     value ="coordinator"
                                 }


                                 gs.storeSharedPreferences(this, gs.LOGINRESPONSE, logindata)
                                 gs.storeSharedPreferences(this, gs.ISMEM, value)
                                 gs.storeSharedPreferences(this, gs.userName, email)
                                 gs.storeSharedPreferences(this, gs.password, password)*/

                                /*var vald: String =  getPayloadFromJwt(loginResponse.data.token)
                                val datak:DataKotlin= Gson().fromJson(
                                     vald.toString(),
                                     DataKotlin::class.java
                                 )
                                 val userid:String?=datak.userid*/

                                /* val intent =
                                     Intent(this@LoginActivity, MainActivity::class.java)
                                 //intent.putExtra(gs.ISMEM, value)
                                 startActivity(intent)*/
                                //val intent = Intent(this@LoginActivity, CountryActivity::class.java)
                                //intent.putExtra(gs.ISMEM, "")
                                // startActivity(intent)
                                // finish()
                            }
                        }
                    }

                    is Resource.Error -> {
                        hideProgressBar()
                        response.message?.let { toast(it) }
                    }

                    is Resource.Loading -> {
                        showProgressBar()
                    }


                    else -> {
                        Toast.makeText(this, "Some problem occured", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

    }

private fun showProgressBar() {
    dialog = gs.showDialog(this)
}

private fun hideProgressBar() {
    dialog?.cancel()
}
}

