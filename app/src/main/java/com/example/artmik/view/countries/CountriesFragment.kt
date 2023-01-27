package com.example.artmik.view.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.artmik.data.remote.models.Country
import com.example.artmik.databinding.FragmentCountriesBinding
import com.example.artmik.view.MainViewModel
import com.example.artmik.view.countries.adapter.CountriesAdapter
import com.example.artmik.view.utils.addFragment
import com.example.artmik.view.utils.whenStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CountriesFragment : Fragment() {

    private val binding: FragmentCountriesBinding by viewBinding(CreateMethod.INFLATE)
    private val viewModel: MainViewModel by activityViewModels()
    private var adapter: CountriesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.update.setOnClickListener { lifecycleScope.launch { viewModel.loadCountries() } }

        adapter = CountriesAdapter()
        adapter!!.setOnItemClickAction { country: Country ->
            lifecycleScope.launch {
                viewModel.setSelectedCountry(country)
                addFragment(CountryFragment())
            }
        }

        binding.countriesList.layoutManager = LinearLayoutManager(requireContext())
        binding.countriesList.adapter = adapter

        viewModel.countriesFlow.onEach { countries ->
            countries?.let {
                adapter!!.submitList(it)
            }
        }.whenStarted(lifecycleScope)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }

}