package pl.piotrskiba.exchangerates.data.currency.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.piotrskiba.exchangerates.data.currency.CurrencyApiService
import pl.piotrskiba.exchangerates.data.currency.repository.CurrencyRepositoryImpl
import pl.piotrskiba.exchangerates.data.date.SystemDateProvider
import pl.piotrskiba.exchangerates.domain.currency.repository.CurrencyRepository
import pl.piotrskiba.exchangerates.domain.date.DateProvider
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CurrencyModule {

    @Binds
    fun bindDateProvider(systemDateProvider: SystemDateProvider): DateProvider

    companion object {

        @Provides
        @Singleton
        fun provideRetrofit(): Retrofit =
            Retrofit
                .Builder()
                .baseUrl("https://api.nbp.pl/api/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        @Provides
        @Singleton
        fun provideCurrencyApiService(retrofit: Retrofit): CurrencyApiService =
            retrofit.create(CurrencyApiService::class.java)

        @Provides
        @Singleton
        fun provideCurrencyRepository(currencyApi: CurrencyApiService): CurrencyRepository =
            CurrencyRepositoryImpl(
                currencyApi = currencyApi,
                dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT),
            )
    }
}
