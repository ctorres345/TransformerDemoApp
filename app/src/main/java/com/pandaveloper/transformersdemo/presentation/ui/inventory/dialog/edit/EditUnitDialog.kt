package com.pandaveloper.transformersdemo.presentation.ui.inventory.dialog.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
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
                cleanInputErrors()
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
                is EditUnitViewState.OnUnitNameError -> showUnitNameError()
                is EditUnitViewState.OnUnitTypeError -> showUnitTypeError()
                is EditUnitViewState.OnUnitTeamError -> showUnitTeamError()
                is EditUnitViewState.OnUnitStrengthError -> showStatError(binding.customStatsLayout.strengthLayout)
                is EditUnitViewState.OnUnitSkillError -> showStatError(binding.customStatsLayout.skillLayout)
                is EditUnitViewState.OnUnitFirepowerError -> showStatError(binding.customStatsLayout.firepowerLayout)
                is EditUnitViewState.OnUnitCourageError -> showStatError(binding.customStatsLayout.courageLayout)
                is EditUnitViewState.OnUnitRankError -> showStatError(binding.customStatsLayout.rankLayout)
                is EditUnitViewState.OnUnitEnduranceError -> showStatError(binding.customStatsLayout.enduranceLayout)
                is EditUnitViewState.OnUnitSpeedError -> showStatError(binding.customStatsLayout.speedLayout)
                is EditUnitViewState.OnUnitIntelligenceError -> showStatError(binding.customStatsLayout.intelligenceLayout)
            }
        }
    }

    private fun generateCustomStats() : UnitStatsUIModel? {
        return if(type != null && type == UnitType.CUSTOM) {
            UnitStatsUIModel(
                strength = getStatAmount(binding.customStatsLayout.strengthInput),
                intelligence = getStatAmount(binding.customStatsLayout.intelligenceInput),
                speed = getStatAmount(binding.customStatsLayout.speedInput),
                endurance = getStatAmount(binding.customStatsLayout.enduranceInput),
                rank = getStatAmount(binding.customStatsLayout.rankInput),
                courage = getStatAmount(binding.customStatsLayout.courageInput),
                firepower = getStatAmount(binding.customStatsLayout.firepowerInput),
                skill = getStatAmount(binding.customStatsLayout.skillInput)
            )
        } else null
    }

    private fun showUnitNameError() {
        showInputError(binding.nameLayout, requireContext().getString(R.string.unit_name_error))
    }

    private fun showUnitTypeError() {
        showInputError(binding.typeLayout, requireContext().getString(R.string.unit_type_error))
    }

    private fun showUnitTeamError() {
        showInputError(binding.teamLayout, requireContext().getString(R.string.unit_team_error))
    }

    private fun showStatError(statLayout: TextInputLayout) {
        showInputError(statLayout, requireContext().getString(R.string.unit_stat_error))
    }

    private fun showInputError(statLayout: TextInputLayout, errorMessage: String) {
        statLayout.error = errorMessage
    }

    private fun getStatAmount(statInput: TextInputEditText) : Int {
        return if(statInput.text.toString().isEmpty()) 0 else statInput.text.toString().toInt()
    }

    private fun cleanInputErrors() {
        binding.nameLayout.error = null
        binding.teamLayout.error = null
        binding.typeLayout.error = null
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