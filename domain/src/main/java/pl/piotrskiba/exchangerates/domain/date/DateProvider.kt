package pl.piotrskiba.exchangerates.domain.date

import java.util.Date

interface DateProvider {

    fun getCurrentDate(): Date
    fun getHistoricalDate(daysAgo: Int): Date
}
