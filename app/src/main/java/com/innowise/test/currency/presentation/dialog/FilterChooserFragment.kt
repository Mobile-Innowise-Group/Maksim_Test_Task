package com.innowise.test.currency.presentation.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.innowise.test.currencty.R
import com.innowise.test.currencty.databinding.FragmentFilterChooserBinding
import com.innowise.test.currency.utils.Constants
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
                val state = FilterState.TagFilter()
                selectedFilterState = state
                byTag.nativeToken.setOnClickListener {
                    proceedChoice(it.isSelected, TokenType.NATIVE, state)
                }
                byTag.ecrToken.setOnClickListener {
                    proceedChoice(it.isSelected, TokenType.ERC_20, state)
                }
                byTag.tcrToken.setOnClickListener {
                    proceedChoice(it.isSelected, TokenType.TRC_20, state)
                }

            }
            acceptButton.setOnClickListener {
                setFragmentResult(
                    Constants.requestCode,
                    bundleOf(Constants.resultKey to selectedFilterState)
                )
                findNavController().popBackStack()
            }
        }
    }

    private fun proceedChoice(isSelected: Boolean, tag: TokenType, state: FilterState.TagFilter) {

        if (isSelected) {
            state.tags.add(tag.value)
        } else {
            state.tags.remove(tag.value)
        }
        selectedFilterState = state

    }

}