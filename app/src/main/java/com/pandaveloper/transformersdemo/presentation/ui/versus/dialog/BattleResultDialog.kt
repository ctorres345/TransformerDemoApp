package com.pandaveloper.transformersdemo.presentation.ui.versus.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.pandaveloper.transformersdemo.R
import com.pandaveloper.transformersdemo.databinding.DialogBattleResultBinding
import com.pandaveloper.transformersdemo.enums.BattleResult
import com.pandaveloper.transformersdemo.model.BattleResultUIModel
import com.pandaveloper.transformersdemo.presentation.base.makeInvisible
import com.pandaveloper.transformersdemo.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BattleResultDialog : DialogFragment() {
    private lateinit var binding: DialogBattleResultBinding
    private var battleResult : BattleResultUIModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.TransformerDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogBattleResultBinding.inflate(inflater, container, false)
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
        battleResult?.let { result ->
            binding.teamIcon.setImageDrawable(
                when(result.result){
                    BattleResult.AUTOBOT -> ContextCompat.getDrawable(requireContext(), R.drawable.autobot_summon_icon)
                    BattleResult.DECEPTICON -> ContextCompat.getDrawable(requireContext(), R.drawable.decepticon_summon_icon)
                    BattleResult.TIE -> ContextCompat.getDrawable(requireContext(), R.drawable.tie_icon)
                    BattleResult.DESTROYED -> ContextCompat.getDrawable(requireContext(), R.drawable.destroyed_icon)
                }
            )

            when(result.result){
                BattleResult.AUTOBOT -> {
                    binding.teamIcon.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.autobot_summon_icon))
                    binding.winnerUnit.text = requireContext().getString(R.string.battle_result_winning_unit, result.winningUnitName)
                    binding.survivorNames.text = requireContext().getString(R.string.battle_result_survivors, result.losingTeamSurvivors)
                }
                BattleResult.DECEPTICON -> {
                    binding.teamIcon.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.decepticon_summon_icon))
                    binding.winnerUnit.text = requireContext().getString(R.string.battle_result_winning_unit, result.winningUnitName)
                    binding.survivorNames.text = requireContext().getString(R.string.battle_result_survivors, result.losingTeamSurvivors)
                }
                BattleResult.TIE -> {
                    binding.teamIcon.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.tie_icon))
                    binding.winningTeam.text = requireContext().getString(R.string.battle_result_tie)
                    binding.winnerUnit.text = requireContext().getString(R.string.battle_result_tie_detail)
                    binding.survivorNames.makeInvisible()
                }
                BattleResult.DESTROYED -> {
                    binding.teamIcon.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.destroyed_icon))
                    binding.winningTeam.text = requireContext().getString(R.string.battle_result_destroyed)
                    binding.winnerUnit.text = requireContext().getString(R.string.battle_result_destroyed_detail)
                    binding.survivorNames.makeInvisible()
                }
            }
        }
    }

    private fun initArguments() {
        (arguments?.getSerializable(Constants.Params.PARAM_BATTLE_RESULT) as? BattleResultUIModel)?.let {
            battleResult = it
        }
    }

}