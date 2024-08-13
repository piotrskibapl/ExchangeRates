package pl.piotrskiba.exchangerates.data.currency

import io.reactivex.rxjava3.core.Single
import pl.piotrskiba.exchangerates.data.currency.model.HistoricalRateTableRemote
import pl.piotrskiba.exchangerates.data.currency.model.RateTableRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApiService {

    @GET("exchangerates/tables/{table}")
    fun getRateTable(
        @Path("table") table: String,
    ): Single<List<RateTableRemote>>

    @GET("exchangerates/rates/{table}/{code}/{startDate}/{endDate}")
    fun getHistoricalRateTable(
        @Path("table") table: String,
        @Path("code") code: String,
        @Path("startDate") startDate: String,
        @Path("endDate") endDate: String,
    ): Single<HistoricalRateTableRemote>
}
