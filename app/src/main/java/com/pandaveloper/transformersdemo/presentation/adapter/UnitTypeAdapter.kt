package com.pandaveloper.transformersdemo.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.pandaveloper.transformersdemo.R
import com.pandaveloper.transformersdemo.enums.UnitType
import com.pandaveloper.transformersdemo.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UnitTypeAdapter @Inject constructor(
    @ApplicationContext context: Context
) : ArrayAdapter<UnitType>(context, R.layout.adapter_single_item, Constants.unitTypes) {
    private val unitTypeList = Constants.unitTypes

    override fun getFilter(): Filter {
        return DisabledFilter()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val itemView : View = view ?: LayoutInflater.from(context).inflate(R.layout.adapter_single_item, parent, false)
        getItem(position)?.let {
            itemView.findViewById<TextView>(R.id.item_name).text = it.name
        }
        return itemView
    }

    private inner class DisabledFilter : Filter() {
        override fun performFiltering(arg0: CharSequence?): FilterResults {
            val result = FilterResults()
            result.values = unitTypeList
            result.count = unitTypeList.size
            return result
        }
        override fun publishResults(arg0: CharSequence?, arg1: FilterResults?) {
            notifyDataSetChanged()
        }
    }

}