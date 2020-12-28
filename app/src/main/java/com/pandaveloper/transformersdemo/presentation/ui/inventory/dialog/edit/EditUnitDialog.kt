package com.pandaveloper.transformersdemo.presentation.ui.inventory.dialog.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pandaveloper.transformersdemo.R
import com.pandaveloper.transformersdemo.databinding.DialogEditUnitBinding
import com.pandaveloper.transformersdemo.enums.UnitTeam
import com.pandaveloper.transformersdemo.enums.UnitType
import com.pandaveloper.transformersdemo.model.TransformerUIModel
import com.pandaveloper.transformersdemo.model.UnitStatsUIModel
import com.pandaveloper.transformersdemo.presentation.adapter.UnitTeamAdapter
import com.pandaveloper.transformersdemo.presentation.adapter.UnitTypeAdapter
import com.pandaveloper.transformersdemo.presentation.base.makeGone
import com.pandaveloper.transformersdemo.presentation.base.makeVisible
import com.pandaveloper.transformersdemo.presentation.base.observe
import com.pandaveloper.transformersdemo.presentation.base.setDebounceOnClickListener
import com.pandaveloper.transformersdemo.util.Constants
import com.pandaveloper.transformersdemo.util.SingleEmitionEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditUnitDialog : BottomSheetDialogFragment(){
    @Inject
    lateinit var teamAdapter: UnitTeamAdapter
    @Inject
    lateinit var typeAdapter: UnitTypeAdapter
    private lateinit var binding: DialogEditUnitBinding
    private val viewModel: EditUnitViewModel by activityViewModels()
    private var team : UnitTeam? = null
    private var type : UnitType? = null
    private var originalUnit : TransformerUIModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogEditUnitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initArguments()
        initUI()
    }

    private fun initArguments() {
        (arguments?.getSerializable(Constants.Params.PARAM_UNIT) as? TransformerUIModel)?.let {
            originalUnit = it
        }
    }

    private fun initUI() {
        binding.updateButton.setDebounceOnClickListener {
            originalUnit?.let {
                cleanCustomStatsErrors()
                viewModel.updateUnit(it, binding.nameInput.text.toString(), team, type, generateCustomStats())
            }
        }
        binding.cancelButton.setDebounceOnClickListener {
            dismiss()
        }
        binding.teamPicker.setAdapter(teamAdapter)
        binding.teamPicker.setOnItemClickListener { _, _, position, _ ->
            teamAdapter.getItem(position)?.let {
                binding.teamLayout.error = null
                team = it
            }
        }
        binding.typePicker.setAdapter(typeAdapter)
        binding.typePicker.setOnItemClickListener { _, _, position, _ ->
            typeAdapter.getItem(position)?.let {
                binding.typeLayout.error = null
                type = it
                if(it == UnitType.CUSTOM) {
                    binding.customStatsLayout.root.makeVisible()
                    originalUnit?.let { unit ->
                        initOriginalCustomStats(unit.unitStats)
                    }
                }else {
                    binding.customStatsLayout.root.makeGone()
                }
            }
        }
        originalUnit?.let {
            binding.nameInput.setText(it.name)
            binding.teamPicker.setText(it.team.name)
            team = it.team
        }
    }

    private fun initOriginalCustomStats(originalStats: UnitStatsUIModel) {
        binding.customStatsLayout.strengthInput.setText(originalStats.strength.toString())
        binding.customStatsLayout.intelligenceInput.setText(originalStats.intelligence.toString())
        binding.customStatsLayout.speedInput.setText(originalStats.speed.toString())
        binding.customStatsLayout.enduranceInput.setText(originalStats.endurance.toString())
        binding.customStatsLayout.rankInput.setText(originalStats.rank.toString())
        binding.customStatsLayout.courageInput.setText(originalStats.courage.toString())
        binding.customStatsLayout.firepowerInput.setText(originalStats.firepower.toString())
        binding.customStatsLayout.skillInput.setText(originalStats.skill.toString())
    }

    private fun initObservers() {
        observe(viewModel.getViewState, ::onViewStateUpdated)
    }

    private fun onViewStateUpdated(event: SingleEmitionEvent<EditUnitViewState>?) {
        event?.peekContent()?.let {
            when(it) {
                is EditUnitViewState.OnUnitNameError -> showUnitNameError(requireContext().getString(R.string.unit_name_error))
                is EditUnitViewState.OnUnitTypeError -> {
                    binding.nameLayout.error = null
                    showUnitTypeError(requireContext().getString(R.string.unit_type_error))
                }
                is EditUnitViewState.OnUnitTeamError -> {
                    binding.nameLayout.error = null
                    showUnitTeamError(requireContext().getString(R.string.unit_team_error))
                }
                is EditUnitViewState.OnUnitStrengthError -> showStrengthError()
                is EditUnitViewState.OnUnitSkillError -> showSkillError()
                is EditUnitViewState.OnUnitFirepowerError -> showFirepowerError()
                is EditUnitViewState.OnUnitCourageError -> showCourageError()
                is EditUnitViewState.OnUnitRankError -> showRankError()
                is EditUnitViewState.OnUnitEnduranceError -> showEnduranceError()
                is EditUnitViewState.OnUnitSpeedError -> showSpeedError()
                is EditUnitViewState.OnUnitIntelligenceError -> showIntelligenceError()
            }
        }
    }

    private fun generateCustomStats() : UnitStatsUIModel? {
        return if(type != null && type == UnitType.CUSTOM) {
            UnitStatsUIModel(
                strength = if(binding.customStatsLayout.strengthInput.text.toString().isEmpty()) 0 else binding.customStatsLayout.strengthInput.text.toString().toInt(),
                intelligence = if(binding.customStatsLayout.intelligenceInput.text.toString().isEmpty()) 0 else binding.customStatsLayout.intelligenceInput.text.toString().toInt(),
                speed = if(binding.customStatsLayout.speedInput.text.toString().isEmpty()) 0 else binding.customStatsLayout.speedInput.text.toString().toInt(),
                endurance = if(binding.customStatsLayout.enduranceInput.text.toString().isEmpty()) 0 else binding.customStatsLayout.enduranceInput.text.toString().toInt(),
                rank = if(binding.customStatsLayout.rankInput.text.toString().isEmpty()) 0 else binding.customStatsLayout.rankInput.text.toString().toInt(),
                courage = if(binding.customStatsLayout.courageInput.text.toString().isEmpty()) 0 else binding.customStatsLayout.courageInput.text.toString().toInt(),
                firepower = if(binding.customStatsLayout.firepowerInput.text.toString().isEmpty()) 0 else binding.customStatsLayout.firepowerInput.text.toString().toInt(),
                skill = if(binding.customStatsLayout.skillInput.text.toString().isEmpty()) 0 else binding.customStatsLayout.skillInput.text.toString().toInt()
            )
        } else null
    }

    private fun showUnitNameError(errorMessage: String) {
        binding.nameLayout.error = errorMessage
    }

    private fun showUnitTypeError(errorMessage: String) {
        binding.typeLayout.error = errorMessage
    }

    private fun showUnitTeamError(errorMessage: String) {
        binding.teamLayout.error = errorMessage
    }

    private fun showStrengthError() {
        binding.customStatsLayout.strengthLayout.error = requireContext().getString(R.string.unit_stat_error)
    }

    private fun showIntelligenceError() {
        binding.customStatsLayout.intelligenceLayout.error = requireContext().getString(R.string.unit_stat_error)
    }

    private fun showSpeedError() {
        binding.customStatsLayout.speedLayout.error = requireContext().getString(R.string.unit_stat_error)
    }

    private fun showEnduranceError() {
        binding.customStatsLayout.enduranceLayout.error = requireContext().getString(R.string.unit_stat_error)
    }

    private fun showRankError() {
        binding.customStatsLayout.rankLayout.error = requireContext().getString(R.string.unit_stat_error)
    }

    private fun showCourageError() {
        binding.customStatsLayout.courageLayout.error = requireContext().getString(R.string.unit_stat_error)
    }

    private fun showFirepowerError() {
        binding.customStatsLayout.firepowerLayout.error = requireContext().getString(R.string.unit_stat_error)
    }

    private fun showSkillError() {
        binding.customStatsLayout.skillLayout.error = requireContext().getString(R.string.unit_stat_error)
    }

    private fun cleanCustomStatsErrors() {
        binding.customStatsLayout.strengthLayout.error = null
        binding.customStatsLayout.intelligenceLayout.error = null
        binding.customStatsLayout.speedLayout.error = null
        binding.customStatsLayout.enduranceLayout.error = null
        binding.customStatsLayout.rankLayout.error = null
        binding.customStatsLayout.courageLayout.error = null
        binding.customStatsLayout.firepowerLayout.error = null
        binding.customStatsLayout.skillLayout.error = null
    }
}