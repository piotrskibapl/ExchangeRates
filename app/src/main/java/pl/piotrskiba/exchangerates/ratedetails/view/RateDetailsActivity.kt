package pl.piotrskiba.exchangerates.ratedetails.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.piotrskiba.exchangerates.base.serializable
import pl.piotrskiba.exchangerates.ratedetails.viewmodel.RateDetailsViewModel

@AndroidEntryPoint
class RateDetailsActivity : ComponentActivity() {

    object Arg {

        const val rate = "rate"
    }

    private val rateDetailsViewModel: RateDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        rateDetailsViewModel.rate = intent.serializable(Arg.rate)!!
        setContent { RateDetailsView(rateDetailsViewModel.rate) }
    }
}
