package pl.piotrskiba.exchangerates.ratelist.nav

import pl.piotrskiba.exchangerates.ratelist.model.Rate

interface RateListNavigator {

    fun navigateToCurrencyDetails(rate: Rate)
}
