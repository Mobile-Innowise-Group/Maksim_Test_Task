package com.innowise.test.currency.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.innowise.test.currencty.R
import com.innowise.test.currencty.databinding.FragmentCurrencyRatesBinding
import com.innowise.test.currency.utils.Constants
import com.innowise.test.currency.presentation.dialog.FilterDialog
import com.innowise.test.currency.presentation.dialog.FilterState
import com.innowise.test.currency.presentation.rates.adapter.CurrencyRatesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyRateFragment : Fragment(R.layout.fragment_currency_rates) {

    private val binding: FragmentCurrencyRatesBinding by viewBinding(FragmentCurrencyRatesBinding::bind)

    private val adapter: CurrencyRatesAdapter by lazy { CurrencyRatesAdapter() }

    private val viewModel by viewModels<CurrencyRateViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(requireContext())

            viewModel.filteredRates.observe(viewLifecycleOwner) { rates ->
                adapter.submitList(rates)
            }
            setFragmentResultListener(Constants.requestCode) { requestKey, bundle ->
                val result = bundle.getParcelable<FilterState>(Constants.resultKey)
                viewModel.onFilterApplied(result ?: FilterState.PriceFilterAsc)
            }
            filterFab.setOnClickListener {
                FilterDialog(requireContext()) { filterType ->
                    findNavController().navigate(
                        CurrencyRateFragmentDirections.actionIdHomeFragmentToFilterChooserFragment2(
                            filterType
                        )
                    )
                }.show()

            }

        }

    }
}