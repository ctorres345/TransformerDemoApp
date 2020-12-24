package com.pandaveloper.transformersdemo.util

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.pandaveloper.transformersdemo.databinding.DialogLoadingBinding
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class LoadingDialog @Inject constructor (@ActivityContext private val context: Context) {
    private var dialog: AlertDialog? = null

    fun showLoading(loadingMessage: String? = null){
        val builder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        val binding = DialogLoadingBinding.inflate(inflater, null, false)
        loadingMessage?.let {
            binding.loadingMessage.text = it
        }
        builder.setView(binding.root)
        builder.setCancelable(false)
        dialog = builder.create()
        dialog?.show()
    }

    fun dismissLoading() {
        dialog?.dismiss()
    }
}