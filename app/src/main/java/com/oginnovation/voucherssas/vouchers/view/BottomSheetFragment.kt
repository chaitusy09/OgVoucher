package com.oginnovation.voucherssas.vouchers.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.oginnovation.voucherssas.R
import com.oginnovation.voucherssas.redemption.RedemptionActivity
import com.oginnovation.voucherssas.scanner.VoucherScanActivity


/**
 * A simple [Fragment] subclass.
 * Use the [BottomSheetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_rounded_bottom_sheet_dialog, container, false)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
        // Handle button clicks here with findViewById or view binding
        val reedemButton = view.findViewById<AppCompatButton>(R.id.redeem_button)
        reedemButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, RedemptionActivity::class.java)
            startActivity(intent)
        })
        return view
    }
}