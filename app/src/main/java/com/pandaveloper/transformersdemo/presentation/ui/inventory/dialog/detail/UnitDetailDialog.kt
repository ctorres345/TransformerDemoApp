package com.pandaveloper.transformersdemo.presentation.ui.inventory.dialog.detail

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.pandaveloper.transformersdemo.R
import com.pandaveloper.transformersdemo.databinding.DialogInventoryUnitDetailBinding
import com.pandaveloper.transformersdemo.enums.UnitTeam
import com.pandaveloper.transformersdemo.model.SummonedUnitUIModel
import com.pandaveloper.transformersdemo.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UnitDetailDialog : DialogFragment() {
    private lateinit var binding: DialogInventoryUnitDetailBinding
    private var unit : SummonedUnitUIModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.TransformerDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogInventoryUnitDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setCancelable(true)
            setCanceledOnTouchOutside(true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArguments()
        initUI()
    }

    private fun initUI() {
        unit?.let {
            binding.teamIcon.setImageDrawable(
                when(it.team){
                    UnitTeam.AUTOBOT -> ContextCompat.getDrawable(requireContext(), R.drawable.autobot_summon_icon)
                    UnitTeam.DECEPTICON -> ContextCompat.getDrawable(requireContext(), R.drawable.decepticon_summon_icon)
                }
            )
            binding.unitName.text = it.displayName
            binding.unitRating.text = it.displayRating
            binding.unitStatsDetail.text = it.displayStats
        }
    }

    private fun initArguments() {
        (arguments?.getSerializable(Constants.BundleParams.PARAM_UNIT) as? SummonedUnitUIModel)?.let {
            unit = it
        }
    }

}