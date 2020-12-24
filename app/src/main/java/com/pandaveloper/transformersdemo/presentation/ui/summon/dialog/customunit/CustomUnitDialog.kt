package com.pandaveloper.transformersdemo.presentation.ui.summon.dialog.customunit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pandaveloper.transformersdemo.databinding.DialogCustomUnitBinding
import com.pandaveloper.transformersdemo.enums.UnitTeam
import com.pandaveloper.transformersdemo.enums.UnitType
import com.pandaveloper.transformersdemo.presentation.adapter.UnitTeamAdapter
import com.pandaveloper.transformersdemo.presentation.adapter.UnitTypeAdapter
import com.pandaveloper.transformersdemo.presentation.base.observe
import com.pandaveloper.transformersdemo.presentation.base.setDebounceOnClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CustomUnitDialog : BottomSheetDialogFragment(){
    @Inject
    lateinit var teamAdapter: UnitTeamAdapter
    @Inject
    lateinit var typeAdapter: UnitTypeAdapter
    private lateinit var binding: DialogCustomUnitBinding
    private val viewModel: CustomUnitViewModel by activityViewModels()
    private var team : UnitTeam? = null
    private var type : UnitType? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogCustomUnitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initUI()
    }

    private fun initUI() {
        binding.saveButton.setDebounceOnClickListener {
            viewModel.saveCustomUnit(binding.nameInput.text.toString(), team, type)
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
    }

    private fun initObservers() {
        observe(viewModel.getViewState, ::onViewStateUpdated)
    }

    private fun onViewStateUpdated(customUnitViewState: CustomUnitViewState?) {
        customUnitViewState?.let {
            when(it) {
                is CustomUnitViewState.OnUnitCreationSuccess -> findNavController().navigateUp()
                is CustomUnitViewState.OnUnitNameError -> showUnitNameError(it.errorMessage)
                is CustomUnitViewState.OnUnitTypeError -> {
                    binding.nameLayout.error = null
                    showUnitTypeError(it.errorMessage)
                }
                is CustomUnitViewState.OnUnitTeamError -> {
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