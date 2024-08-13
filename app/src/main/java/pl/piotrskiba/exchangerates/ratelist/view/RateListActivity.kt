package pl.piotrskiba.exchangerates.ratelist.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.piotrskiba.exchangerates.ratedetails.view.RateDetailsActivity
import pl.piotrskiba.exchangerates.ratelist.model.Rate
import pl.piotrskiba.exchangerates.ratelist.nav.RateListNavigator
import pl.piotrskiba.exchangerates.ratelist.viewmodel.RateListViewModel

@AndroidEntryPoint
class RateListActivity : ComponentActivity(), RateListNavigator {

    private val rateListViewModel: RateListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { RateListView(rateListViewModel.rateList, rateListViewModel.state, rateListViewModel::onRateClick) }
        rateListViewModel.navigator = this
        rateListViewModel.loadRateList()
    }

    override fun navigateToCurrencyDetails(rate: Rate) {
        val intent = Intent(this, RateDetailsActivity::class.java)
        intent.putExtra(RateDetailsActivity.Arg.rate, rate)
        startActivity(intent)
    }
}
