package com.innowise.test.currency.presentation.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.checkbox.MaterialCheckBox
import com.innowise.test.currencty.R
import com.innowise.test.currencty.databinding.FragmentFilterChooserBinding
import dagger.hilt.android.AndroidEntryPoint

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class FilterChooserFragment : Fragment(R.layout.fragment_filter_chooser) {

    private val arguments by navArgs<FilterChooserFragmentArgs>()

    private val filterType by lazy { arguments.filterType }

    private lateinit var selectedFilterState: FilterState

    private val binding by viewBinding(FragmentFilterChooserBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            byTag.root.isVisible = arguments.filterType == FilterType.TAG
            if (filterType == FilterType.EQUIVALENT) {
                selectedFilterState = FilterState.PriceFilterAsc
                byPrice.root.isVisible = true
                byPrice.ascending.setOnClickListener {
                    selectedFilterState = FilterState.PriceFilterAsc
                }
                byPrice.descending.setOnClickListener {
                    selectedFilterState = FilterState.PriceFilterDesc
                }

            } else {
                selectedFilterState = FilterState.TagFilter()
                byTag.nativeToken.setOnClickListener {
                    selectedFilterState = selectedFilterState()
                }
                byTag.ecrToken.setOnClickListener {

                }
                byTag.tcrToken.setOnClickListener {

                }

            }
            acceptButton.setOnClickListener {

            }
        }
    }


}