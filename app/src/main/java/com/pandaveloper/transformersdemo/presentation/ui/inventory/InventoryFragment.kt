package com.pandaveloper.transformersdemo.presentation.ui.inventory

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandaveloper.transformersdemo.R
import com.pandaveloper.transformersdemo.databinding.FragmentInventoryBinding
import com.pandaveloper.transformersdemo.model.TransformerUIModel
import com.pandaveloper.transformersdemo.presentation.adapter.TransformerAdapter
import com.pandaveloper.transformersdemo.presentation.base.BaseViewModelFragment
import com.pandaveloper.transformersdemo.presentation.base.makeInvisible
import com.pandaveloper.transformersdemo.presentation.base.makeVisible
import com.pandaveloper.transformersdemo.presentation.base.observe
import com.pandaveloper.transformersdemo.presentation.ui.inventory.dialog.edit.EditUnitViewModel
import com.pandaveloper.transformersdemo.presentation.ui.inventory.dialog.edit.EditUnitViewState
import com.pandaveloper.transformersdemo.util.Constants
import com.pandaveloper.transformersdemo.util.LoadingDialog
import com.pandaveloper.transformersdemo.util.SingleEmitionEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InventoryFragment : BaseViewModelFragment() {
    @Inject
    lateinit var loadingDialog: LoadingDialog
    private lateinit var binding : FragmentInventoryBinding
    private lateinit var adapter : TransformerAdapter
    private val viewModel : InventoryViewModel by viewModels()
    private val sharedViewModel : EditUnitViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInventoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initAdapter() {
        adapter = TransformerAdapter(callback = viewModel)
    }

    override fun initObservers() {
        observe(viewModel.getViewState, ::onViewStateUpdated)
        observe(sharedViewModel.getViewState, ::onSharedViewStateUpdated)
    }

    private fun onSharedViewStateUpdated(event: SingleEmitionEvent<EditUnitViewState>?) {
        event?.getContentIfNotHandled()?.let {
            if(it is EditUnitViewState.OnUnitUpdated){
                findNavController().navigateUp()
                loadingDialog.showLoading()
                viewModel.updateUnit(it.transformer)
            }
        }
    }

    override fun initUI() {
        loadingDialog.showLoading()
        binding.inventoryList.layoutManager = LinearLayoutManager(requireContext())
        binding.inventoryList.adapter = adapter
        binding.searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    filter(s.toString())
                }
            }
        })
        viewModel.getTransformerInventory()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }

    private fun onViewStateUpdated(inventoryViewState: InventoryViewState?) {
        inventoryViewState?.let {
            when(it) {
                is InventoryViewState.OnLoading -> showLoading()
                is InventoryViewState.OnInventoryReceivedSuccess -> {
                    loadingDialog.dismissLoading()
                    updateInventory(it.transformerList)
                }
                is InventoryViewState.OnInventoryReceivedError -> {
                    loadingDialog.dismissLoading()
                    hideInventory()
                    Toast.makeText(
                        requireContext(),
                        it.errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is InventoryViewState.OnTransformerDeletedSuccess -> {
                    loadingDialog.dismissLoading()
                    updateInventory(it.transformerList)
                }
                is InventoryViewState.OnTransformerDeletedError -> {
                    loadingDialog.dismissLoading()
                    hideInventory()
                    showErrorToast(it.errorMessage)
                }
                is InventoryViewState.OnTransformerEditedSuccess -> {
                    loadingDialog.dismissLoading()
                    updateInventory(it.transformerList)
                }
                is InventoryViewState.OnTransformerEditedError -> {
                    loadingDialog.dismissLoading()
                    hideInventory()
                    showErrorToast(it.errorMessage)
                }
                is InventoryViewState.ShowEditDialog -> {
                    val bundle = bundleOf(Constants.BundleParams.PARAM_UNIT to it.transformer)
                    findNavController().navigate(R.id.editUnitDialog, bundle)
                }
                is InventoryViewState.ShowUnitDetailDialog -> {
                    val bundle = bundleOf(Constants.BundleParams.PARAM_UNIT to it.unit)
                    findNavController().navigate(R.id.inventoryUnitDetailDialog, bundle)
                }
            }
        }
    }

    private fun showLoading() {
        loadingDialog.showLoading()
    }

    private fun showErrorToast(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun filter(text: String) {
        when {
            text.isEmpty() -> adapter.updateList(viewModel.getInventory())
            else -> {
                val filteredList = viewModel.getInventory().filter { it.name.toLowerCase().startsWith(text.toLowerCase()) }
                adapter.updateList(filteredList)
            }
        }
    }

    private fun updateInventory(transformerList: List<TransformerUIModel>) {
        if (transformerList.isEmpty()){
            hideInventory()
        } else {
            showInventory()
        }
        adapter.updateList(transformerList)
    }

    private fun showInventory(){
        binding.inventoryList.makeVisible()
        binding.noResultMessage.makeInvisible()
    }

    private fun hideInventory(){
        binding.inventoryList.makeInvisible()
        binding.noResultMessage.makeVisible()
    }
}