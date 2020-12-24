package com.pandaveloper.transformersdemo.presentation.ui.versus

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pandaveloper.transformersdemo.R
import com.pandaveloper.transformersdemo.databinding.FragmentVersusBinding
import com.pandaveloper.transformersdemo.presentation.base.BaseViewModelFragment
import com.pandaveloper.transformersdemo.presentation.base.observe
import com.pandaveloper.transformersdemo.presentation.base.setDebounceOnClickListener
import com.pandaveloper.transformersdemo.util.Constants
import com.pandaveloper.transformersdemo.util.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VersusFragment : BaseViewModelFragment() {
    @Inject
    lateinit var loadingDialog: LoadingDialog
    private lateinit var binding: FragmentVersusBinding
    private val viewModel : VersusViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVersusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initUI() {
        super.initUI()
        binding.battleButton.setDebounceOnClickListener {
            viewModel.performBattle()
        }
        loadingDialog.showLoading("Verifying Teams...")
        viewModel.validateTeams()
    }

    override fun initObservers() {
        observe(viewModel.getViewState, ::onViewStateUpdated)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }

    private fun onViewStateUpdated(versusViewState: VersusViewState?) {
        versusViewState?.let {
            when(it){
                is VersusViewState.TeamValidationSuccess -> {
                    loadingDialog.dismissLoading()
                    binding.autobotTeam.text = viewModel.getAutobotLineup()
                    binding.decepticonTeam.text = viewModel.getDecepticonLineup()
                }
                is VersusViewState.NoUnitsError -> {
                    loadingDialog.dismissLoading()
                    findNavController().navigateUp()
                    Toast.makeText(requireContext(), "You don't have any unit in your inventory. Please select summon and recruit some units.", Toast.LENGTH_LONG).show()
                }
                is VersusViewState.NoAutobotsError -> {
                    loadingDialog.dismissLoading()
                    findNavController().navigateUp()
                    Toast.makeText(requireContext(), "You don't have any Autobot unit in your inventory. Please select summon and recruit some units.", Toast.LENGTH_LONG).show()
                }
                is VersusViewState.NoDecepticonsError -> {
                    loadingDialog.dismissLoading()
                    findNavController().navigateUp()
                    Toast.makeText(requireContext(), "You don't have any Decepticon unit in your inventory. Please select summon and recruit some units.", Toast.LENGTH_LONG).show()
                }
                is VersusViewState.OnBattleResults -> {
                    loadingDialog.dismissLoading()
                    val bundle = bundleOf(Constants.BundleParams.PARAM_BATTLE_RESULT to it.result)
                    findNavController().navigate(R.id.battleResultDialog, bundle)
                }
            }
        }
    }
}