package pl.piotrskiba.exchangerates.ratelist.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.piotrskiba.exchangerates.ratelist.viewmodel.RateListViewModel

@AndroidEntryPoint
class RateListActivity : ComponentActivity() {

    private val rateListViewModel: RateListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        rateListViewModel.loadRateList()
    }
}
