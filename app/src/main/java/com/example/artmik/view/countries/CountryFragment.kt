package com.example.artmik.view.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.artmik.R
import com.example.artmik.databinding.FragmentCountryBinding
import com.example.artmik.view.MainViewModel
import com.example.artmik.view.MainViewModelFactory
import com.example.artmik.view.utils.load
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CountryFragment : Fragment() {

    private val binding: FragmentCountryBinding by viewBinding(CreateMethod.INFLATE)
    private val viewModel: MainViewModel by viewModels { mainViewModelFactory}

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val country = viewModel.selectedCountry.value

        country?.let {
            binding.name.text = it.name
            binding.flag.load(it.flags.png)
            binding.region.text = getString(R.string.text_region, it.region)
            binding.capital.text = getString(R.string.text_capital, it.capital)
            val currency = getString(
                R.string.text_currency,
                it.currencies.first().name,
                it.currencies.first().symbol
            )
            binding.currency.text = currency
            val timezones = getString(R.string.text_timezones, it.timezones.joinToString(", "))
            binding.timezones.text = timezones
        }

    }

}