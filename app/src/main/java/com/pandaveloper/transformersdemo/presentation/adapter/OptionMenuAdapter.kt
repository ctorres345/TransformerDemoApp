package com.pandaveloper.transformersdemo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pandaveloper.transformersdemo.R
import com.pandaveloper.transformersdemo.databinding.MenuOptionItemBinding
import com.pandaveloper.transformersdemo.enums.MenuOption
import com.pandaveloper.transformersdemo.model.MenuOptionUIModel
import com.pandaveloper.transformersdemo.presentation.base.setDebounceOnClickListener
import com.pandaveloper.transformersdemo.presentation.callback.OptionMenuAdapterCallback
import com.pandaveloper.transformersdemo.util.Constants

class OptionMenuAdapter(
    private val optionList: List<MenuOptionUIModel> = Constants.menuOptions,
    private val callback: OptionMenuAdapterCallback
) : RecyclerView.Adapter<OptionMenuAdapter.OptionMenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionMenuViewHolder {
        return OptionMenuViewHolder(MenuOptionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: OptionMenuViewHolder, position: Int) {
        holder.bind(optionList[position])
    }

    override fun getItemCount() = optionList.size

    inner class OptionMenuViewHolder(private val binding: MenuOptionItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(option: MenuOptionUIModel) {
            binding.optionIcon.setImageDrawable(
                ContextCompat.getDrawable(
                    binding.root.context,
                    when(option.option){
                        MenuOption.INVENTORY -> {
                            R.drawable.transformer_inventory_icon
                        }
                        MenuOption.SUMMON -> {
                            R.drawable.transformer_creation_icon
                        }
                        MenuOption.BATTLE -> {
                            R.drawable.transformer_versus
                        }
                        MenuOption.EXIT -> {
                            R.drawable.exit_icon
                        }
                    }
                )
            )
            binding.optionName.text = option.displayName
            binding.root.setDebounceOnClickListener {
                callback.onOptionSelected(option)
            }
        }
    }
}