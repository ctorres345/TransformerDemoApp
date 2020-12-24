package com.pandaveloper.transformersdemo.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.pandaveloper.data.repository.local.SessionManager
import com.pandaveloper.transformersdemo.R
import com.pandaveloper.transformersdemo.databinding.FragmentHomeBinding
import com.pandaveloper.transformersdemo.enums.MenuOption
import com.pandaveloper.transformersdemo.model.MenuOptionUIModel
import com.pandaveloper.transformersdemo.presentation.adapter.OptionMenuAdapter
import com.pandaveloper.transformersdemo.presentation.base.BaseFragment
import com.pandaveloper.transformersdemo.presentation.callback.OptionMenuAdapterCallback
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment(), OptionMenuAdapterCallback {
    @Inject
    lateinit var sessionManager: SessionManager
    private lateinit var adapter : OptionMenuAdapter
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initUI() {
        binding.optionList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.optionList.adapter = adapter
    }

    override fun initAdapter() {
        super.initAdapter()
        adapter = OptionMenuAdapter(callback = this)
    }

    override fun onOptionSelected(option: MenuOptionUIModel) {
        when(option.option){
            MenuOption.INVENTORY -> findNavController().navigate(R.id.action_homeFragment_to_InventoryFragment)
            MenuOption.SUMMON -> findNavController().navigate(R.id.action_homeFragment_to_summonFragment)
            MenuOption.BATTLE -> findNavController().navigate(R.id.action_homeFragment_to_versusFragment)
            MenuOption.EXIT -> {
                sessionManager.clearData()
                requireActivity().finish()
            }
        }
    }
}