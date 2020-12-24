package com.pandaveloper.transformersdemo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pandaveloper.transformersdemo.R
import com.pandaveloper.transformersdemo.databinding.TransformerItemBinding
import com.pandaveloper.transformersdemo.enums.UnitTeam
import com.pandaveloper.transformersdemo.model.TransformerUIModel
import com.pandaveloper.transformersdemo.presentation.base.setDebounceOnClickListener
import com.pandaveloper.transformersdemo.presentation.callback.TransformerAdapterCallback
import com.pandaveloper.transformersdemo.util.calculateRating

class TransformerAdapter(
    private val transformerList: MutableList<TransformerUIModel> = mutableListOf(),
    private val callback: TransformerAdapterCallback
) : RecyclerView.Adapter<TransformerAdapter.TransformerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransformerViewHolder {
        return TransformerViewHolder(TransformerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TransformerViewHolder, position: Int) {
        holder.bind(transformerList[position])
    }

    override fun getItemCount() = transformerList.size

    fun updateList(newList: List<TransformerUIModel>){
        transformerList.apply {
            clear()
            addAll(newList)
        }
        notifyDataSetChanged()
    }

    inner class TransformerViewHolder(private val binding: TransformerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(unit: TransformerUIModel) {
            binding.unitName.text = "Name: ${unit.name}"
            binding.unitRating.text = "Overall Rating: ${unit.unitStats.calculateRating()}"
            binding.unitTeam.text = "Team: ${unit.team}"
            binding.unitTypeImage.setImageDrawable(
                when(unit.team) {
                    UnitTeam.AUTOBOT -> ContextCompat.getDrawable(binding.root.context, R.drawable.autobot_summon_icon)
                    UnitTeam.DECEPTICON -> ContextCompat.getDrawable(binding.root.context, R.drawable.decepticon_summon_icon)
                }
            )
            binding.root.setDebounceOnClickListener {
                callback.onTransformerSelected(unit)
            }
            binding.deleteButton.setDebounceOnClickListener {
                callback.deleteTransformer(unit.id)
            }
            binding.editButton.setDebounceOnClickListener {
                callback.editTransformer(unit)
            }
        }

    }
}