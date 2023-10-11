package com.innowise.test.currency.presentation.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import com.innowise.test.currencty.databinding.FilterDialogBinding

class FilterDialog(
    context: Context,
    private val navigateToFilter: (FilterType) -> Unit,
) : Dialog(context) {
    private lateinit var binding: FilterDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(true)
        this.window?.addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND)
        setOnCancelListener { dismiss() }
        binding = FilterDialogBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        binding.sortBy.setOnClickListener {
            navigateToFilter(FilterType.EQUIVALENT)
            dismiss()
        }
        binding.tagSort.setOnClickListener {
            navigateToFilter(FilterType.TAG)
            dismiss()
        }
    }
}

enum class FilterType {
    EQUIVALENT,
    TAG,
}