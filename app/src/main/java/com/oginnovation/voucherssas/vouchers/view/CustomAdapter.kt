package com.oginnovation.voucherssas.vouchers.view

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oginnovation.voucherssas.R
import com.oginnovation.voucherssas.vouchers.model.ChargerStation


class CustomAdapter(private val mList: ArrayList<ChargerStation>, mcontext: Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(

) {

    val mcontexts=mcontext
    private val fullList = mList.toMutableList()
    private var currentQuery = ""  // Stores the current search query
    private val searchEditText: EditText? = null

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_custom, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        // sets the text to the textview from our itemHolder class

       /* if(mList.get(position).station_name!=null){
            holder.txtStationName.text = "Station Name: "+mList.get(position).station_name
        }
        if(mList.get(position).station_phone!=null){
            holder.txtStationPhone.text = "Station Phone: "+mList.get(position).station_phone
        }
        if(mList.get(position).address!=null){
            holder.txtaddress.text = "Address: "+mList.get(position).address
        }
        if(mList.get(position).city!=null){
            holder.txtCity.text = "City: "+mList.get(position).city
        }
        if(mList.get(position).state!=null){
            holder.txtState.text = "State: "+mList.get(position).state
        }
        if(mList.get(position).zipcode!=null){
            holder.txtZipCode.text = "Zipcode: "+mList.get(position).zipcode
        }*/


       /* if(mList.get(position).latitude!=null && mList.get(position).latitude!=null){
            holder.btnMap.visibility= VISIBLE
        }else{
            holder.btnMap.visibility= GONE
        }*/

       /* holder.btnMap.setOnClickListener(View.OnClickListener {

            val intent = Intent(mcontexts, MapsActivity::class.java)
            intent.putExtra("lat",mList.get(position).latitude)
            intent.putExtra("lng",mList.get(position).longitude)
            mcontexts.startActivity(intent)
        })*/

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
       // return mList.size
        return 10
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

       /* val txtStationName: TextView = itemView.findViewById(R.id.txtStationName)
        val txtStationPhone: TextView = itemView.findViewById(R.id.txtStationPhone)
        val txtaddress: TextView = itemView.findViewById(R.id.txtaddress)
        val txtCity: TextView = itemView.findViewById(R.id.txtCity)
        val txtState: TextView = itemView.findViewById(R.id.txtState)
        val txtZipCode: TextView = itemView.findViewById(R.id.txtZipCode)
        val btnMap: Button = itemView.findViewById(R.id.btnMap)*/
    }
    // Search filter based on user input
    private val filterTextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            currentQuery = s.toString().lowercase().trim()
            filterList(currentQuery)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    // Filter list based on search query
    fun filterList(query: String) {
        val filteredList = fullList.filter { station ->
            val stationText = "${station.station_name ?: ""} ${station.station_phone ?: ""} ${station.address ?: ""} ${station.city ?: ""} ${station.state ?: ""} ${station.zipcode ?: ""}"
            stationText.lowercase().contains(query.lowercase())
        }
        mList.clear()
        mList.addAll(filteredList)
        notifyDataSetChanged() // Update UI with filtered list
    }
}