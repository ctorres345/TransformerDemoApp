package com.pandaveloper.transformersdemo.presentation.ui.inventory.dialog.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pandaveloper.transformersdemo.databinding.DialogEditUnitBinding
import com.pandaveloper.transformersdemo.enums.UnitTeam
import com.pandaveloper.transformersdemo.enums.UnitType
import com.pandaveloper.transformersdemo.model.TransformerUIModel
import com.pandaveloper.transformersdemo.presentation.adapter.UnitTeamAdapter
import com.pandaveloper.transformersdemo.presentation.adapter.UnitTypeAdapter
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
        (arguments?.getSerializable(Constants.BundleParams.PARAM_UNIT) as? TransformerUIModel)?.let {
            originalUnit = it
        }
    }

    private fun initUI() {
        binding.updateButton.setDebounceOnClickListener {
            originalUnit?.let {
                viewModel.updateUnit(it, binding.nameInput.text.toString(), team, type)
            }
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
            }
        }
        originalUnit?.let {
            binding.nameInput.setText(it.name)
            binding.teamPicker.setText(it.team.name)
            team = it.team
        }
    }

    private fun initObservers() {
        observe(viewModel.getViewState, ::onViewStateUpdated)
    }

    private fun onViewStateUpdated(event: SingleEmitionEvent<EditUnitViewState>?) {
        event?.getContentIfNotHandled()?.let {
            when(it) {
                is EditUnitViewState.OnUnitNameError -> showUnitNameError(it.errorMessage)
                is EditUnitViewState.OnUnitTypeError -> {
                    binding.nameLayout.error = null
                    showUnitTypeError(it.errorMessage)
                }
                is EditUnitViewState.OnUnitTeamError -> {
                    binding.nameLayout.error = null
                    showUnitTeamError(it.errorMessage)
                }
            }
        }
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
}