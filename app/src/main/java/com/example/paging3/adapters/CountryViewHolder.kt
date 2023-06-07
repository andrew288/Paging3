package com.example.paging3.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3.R
import com.example.paging3.data.model.Country

class CountryViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val name_en = view.findViewById<TextView>(R.id.textViewNameEn) as TextView
    private val continet_en = view.findViewById<TextView>(R.id.textViewContinentEn) as TextView
    private val capital_en = view.findViewById<TextView>(R.id.textViewCapitalEn) as TextView

    fun bind(country: Country) {
        with(country) {
            name_en = name_en.toString()
            continent_en = continet_en.toString()
            capital_en = capital_en.toString()
        }
    }
}