package pl.piotrskiba.exchangerates.ratedetails.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.piotrskiba.exchangerates.ratelist.model.Rate
import javax.inject.Inject

@HiltViewModel
class RateDetailsViewModel @Inject constructor() : ViewModel() {

    lateinit var rate: Rate
}
