package pl.piotrskiba.exchangerates.data.currency.repository

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pl.piotrskiba.exchangerates.data.currency.CurrencyApiService
import pl.piotrskiba.exchangerates.data.currency.model.HistoricalRateTableRemote
import pl.piotrskiba.exchangerates.data.currency.model.RateTableRemote
import pl.piotrskiba.exchangerates.data.currency.model.toDomain
import pl.piotrskiba.exchangerates.domain.currency.model.HistoricalRateTableModel
import pl.piotrskiba.exchangerates.domain.currency.model.RateTableModel
import pl.piotrskiba.exchangerates.domain.currency.model.TableModel
import pl.piotrskiba.exchangerates.domain.currency.model.toRemote
import java.text.SimpleDateFormat
import java.util.Date

class CurrencyRepositoryImplTest {

    val currencyApi: CurrencyApiService = mockk()
    val dateFormat: SimpleDateFormat = mockk()
    val tested = CurrencyRepositoryImpl(currencyApi, dateFormat)

    @BeforeEach
    fun setup() {
        mockkStatic(
            TableModel::toRemote,
            RateTableRemote::toDomain,
            HistoricalRateTableRemote::toDomain,
        )
    }

    @AfterEach
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun `SHOULD get rate table from CurrencyApiService WHEN getRateTable called`() {
        val rateTableModel: RateTableModel = mockk()
        val rateTableRemote: RateTableRemote = mockk {
            every { toDomain() } returns rateTableModel
        }
        val table: TableModel = mockk {
            every { toRemote() } returns "A"
        }
        every { currencyApi.getRateTable("A") } returns Single.just(listOf(rateTableRemote))

        val result = tested.getRateTable(table).test()

        result.assertValue(rateTableModel)
    }

    @Test
    fun `SHOULD get historical rate table from CurrencyApiService WHEN getHistoricalRateTable called`() {
        val table: TableModel = mockk {
            every { toRemote() } returns "A"
        }
        val code = "PLN"
        val startDate: Date = mockk()
        val endDate: Date = mockk()
        val historicalRateTableModel: HistoricalRateTableModel = mockk()
        val historicalRateTableRemote: HistoricalRateTableRemote = mockk {
            every { toDomain() } returns historicalRateTableModel
        }
        every { dateFormat.format(startDate) } returns "2020-01-01"
        every { dateFormat.format(endDate) } returns "2020-01-02"
        every { currencyApi.getHistoricalRateTable("A", code, "2020-01-01", "2020-01-02") } returns Single.just(historicalRateTableRemote)

        val result = tested.getHistoricalRateTable(table, code, startDate, endDate).test()

        result.assertValue(historicalRateTableModel)
    }
}
