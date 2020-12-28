package com.pandaveloper.transformersdemo.presentation.ui.summon.dialog.result

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.pandaveloper.transformersdemo.R
import com.pandaveloper.transformersdemo.databinding.DialogSummonBinding
import com.pandaveloper.transformersdemo.enums.UnitTeam
import com.pandaveloper.transformersdemo.model.SummonedUnitUIModel
import com.pandaveloper.transformersdemo.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummonResultDialog : DialogFragment() {
    private lateinit var binding: DialogSummonBinding
    private var summonedUnit : SummonedUnitUIModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.TransformerDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSummonBinding.inflate(inflater, container, false)
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
        summonedUnit?.let {
            binding.teamIcon.setImageDrawable(
                when(it.team){
                    UnitTeam.AUTOBOT -> ContextCompat.getDrawable(requireContext(), R.drawable.autobot_summon_icon)
                    UnitTeam.DECEPTICON -> ContextCompat.getDrawable(requireContext(), R.drawable.decepticon_summon_icon)
                }
            )
            binding.unitName.text = requireContext().getString(R.string.summoned_unit_name, it.unitName, it.unitStars.toString())
            binding.unitRating.text = requireContext().getString(R.string.summoned_unit_rating, it.unitRating.toString())
        }
    }

    private fun initArguments() {
        (arguments?.getSerializable(Constants.Params.PARAM_UNIT) as? SummonedUnitUIModel)?.let {
            summonedUnit = it
        }
    }
}