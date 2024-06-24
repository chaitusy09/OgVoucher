package com.oginnovation.voucherssas.vouchers.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.Switch
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.oginnovation.voucherssas.R
import com.oginnovation.voucherssas.redemption.RedemptionActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BootomSheetDialogueReedemCode.newInstance] factory method to
 * create an instance of this fragment.
 */
class BootomSheetDialogueReedemCode : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_bootom_sheet_dialogue_reedem_code, container, false)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
        // Handle button clicks here with findViewById or view binding
        val mySwitch = view.findViewById<SwitchCompat>(R.id.switch_view)
        val linearRedem:LinearLayout=view.findViewById<LinearLayout>(R.id.linearRedem)
        val edtNameofreedem:AppCompatEditText=view.findViewById<AppCompatEditText>(R.id.edtNameofreedem)
        val edtAmount:AppCompatEditText=view.findViewById<AppCompatEditText>(R.id.edtAmount)
        // Set a listener for switch changes
        mySwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            // Handle switch state change
            // isChecked will be true if the switch is on, false if off
            if (isChecked) {
                // Switch is turned on
                // Perform actions for the on state
                linearRedem.visibility=View.VISIBLE
                edtAmount.setText("")
                edtNameofreedem.setText("")
            } else {
                // Switch is turned off
                // Perform actions for the off state
                linearRedem.visibility=View.GONE
                edtAmount.setText("")
                edtNameofreedem.setText("")
            }
        }
        val reedemButton = view.findViewById<AppCompatButton>(R.id.redeem_button)
        reedemButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, RedemptionActivity::class.java)
            startActivity(intent)
        })
        return view
    }
}