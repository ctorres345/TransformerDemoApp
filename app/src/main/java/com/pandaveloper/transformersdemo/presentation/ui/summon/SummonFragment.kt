package com.pandaveloper.transformersdemo.presentation.ui.summon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pandaveloper.transformersdemo.R
import com.pandaveloper.transformersdemo.databinding.FragmentSummonBinding
import com.pandaveloper.transformersdemo.model.SummonedUnitUIModel
import com.pandaveloper.transformersdemo.presentation.base.BaseViewModelFragment
import com.pandaveloper.transformersdemo.presentation.base.observe
import com.pandaveloper.transformersdemo.presentation.base.setDebounceOnClickListener
import com.pandaveloper.transformersdemo.presentation.ui.summon.dialog.customunit.CustomUnitViewModel
import com.pandaveloper.transformersdemo.presentation.ui.summon.dialog.customunit.CustomUnitViewState
import com.pandaveloper.transformersdemo.util.Constants
import com.pandaveloper.transformersdemo.util.LoadingDialog
import com.pandaveloper.transformersdemo.util.SingleEmitionEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SummonFragment : BaseViewModelFragment() {
    @Inject
    lateinit var loadingDialog: LoadingDialog
    private val viewModel : SummonViewModel by viewModels()
    private val sharedViewModel : CustomUnitViewModel by activityViewModels()
    private lateinit var binding: FragmentSummonBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSummonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initUI() {
        binding.summonIcon.setDebounceOnClickListener {
            loadingDialog.showLoading("Summoning Unit...")
            viewModel.summonUnit()
        }
        binding.createButton.setDebounceOnClickListener {
            findNavController().navigate(R.id.customUnitDialog)
        }
    }

    override fun initObservers() {
        observe(viewModel.getViewState, ::onViewStateUpdated)
        observe(sharedViewModel.getViewState, ::onSharedViewStateUpdated)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }

    private fun onSharedViewStateUpdated(event: SingleEmitionEvent<CustomUnitViewState>?) {
        event?.getContentIfNotHandled()?.let {
            if(it is CustomUnitViewState.OnUnitCreationSuccess){
                findNavController().navigateUp()
                loadingDialog.showLoading()
                viewModel.registerTransformer(it.transformer)
            }
        }
    }

    private fun onViewStateUpdated(summonViewState: SummonViewState?) {
        summonViewState?.let {
            when(it) {
                is SummonViewState.OnUnitSummonSuccess -> {
                    loadingDialog.dismissLoading()
                    showSummonedUnit(it.summonedUnit)
                }
                is SummonViewState.OnUnitSummonError -> {
                    loadingDialog.dismissLoading()
                    Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun showSummonedUnit(summonedUnit: SummonedUnitUIModel) {
        val bundle = bundleOf(Constants.BundleParams.PARAM_UNIT to summonedUnit)
        findNavController().navigate(R.id.summonDialog, bundle)
    }
}