package pl.piotrskiba.exchangerates.domain.date

import java.util.Date

class TestDateProvider(private val fixedDate: Date, private val fixedHistoricalDate: Date) : DateProvider {

    override fun getCurrentDate(): Date = fixedDate

    override fun getHistoricalDate(daysAgo: Int): Date = fixedHistoricalDate
}
