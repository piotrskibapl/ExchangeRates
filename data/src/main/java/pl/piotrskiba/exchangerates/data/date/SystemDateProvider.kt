package pl.piotrskiba.exchangerates.data.date

import pl.piotrskiba.exchangerates.domain.date.DateProvider
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class SystemDateProvider @Inject constructor() : DateProvider {

    override fun getCurrentDate(): Date = Calendar.getInstance().time
    override fun getHistoricalDate(daysAgo: Int): Date =
        Calendar.getInstance()
            .apply { add(Calendar.DAY_OF_YEAR, -daysAgo) }
            .time
}
