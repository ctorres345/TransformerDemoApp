package com.pandaveloper.transformersdemo.presentation.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseViewModelFragment : Fragment(), FragmentComponents, ViewModelFragmentComponents{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArguments()
        initObservers()
        initAdapter()
        initUI()
    }
}