package com.pandaveloper.transformersdemo.presentation.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pandaveloper.data.repository.local.SessionManager
import com.pandaveloper.transformersdemo.R
import com.pandaveloper.transformersdemo.databinding.FragmentStartBinding
import com.pandaveloper.transformersdemo.presentation.base.BaseViewModelFragment
import com.pandaveloper.transformersdemo.presentation.base.observe
import com.pandaveloper.transformersdemo.presentation.base.setDebounceOnClickListener
import com.pandaveloper.transformersdemo.util.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StartFragment : BaseViewModelFragment() {
    @Inject
    lateinit var sessionManager: SessionManager
    @Inject
    lateinit var loadingDialog: LoadingDialog
    private lateinit var binding: FragmentStartBinding

    private val viewModel : StartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initObservers() {
        observe(viewModel.getViewState, ::onViewStateUpdated)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }

    private fun onViewStateUpdated(state: StartViewState?) {
        state?.let {
            when(it){
                is StartViewState.OnTokenReceivedSuccess -> {
                    loadingDialog.dismissLoading()
                    sessionManager.saveToken(it.token)
                    Toast.makeText(requireContext(),"token received :${it.token}", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_startFragment_to_homeFragment)
                }
                is StartViewState.OnTokenReceivedError -> {
                    loadingDialog.dismissLoading()
                    Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun initUI() {
        binding.root.setDebounceOnClickListener {
            when {
                sessionManager.getToken() == null -> {
                    loadingDialog.showLoading()
                    viewModel.startGame()
                }
                else -> findNavController().navigate(R.id.action_startFragment_to_homeFragment)
            }
        }
    }
}