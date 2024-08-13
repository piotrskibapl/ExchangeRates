package pl.piotrskiba.exchangerates.base.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.piotrskiba.exchangerates.base.rx.SchedulersFacade
import pl.piotrskiba.exchangerates.base.rx.SchedulersProvider
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    abstract fun bindSchedulersProvider(schedulersFacade: SchedulersFacade): SchedulersProvider

    companion object {

        @Provides
        @Singleton
        fun provideDateFormat(): SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)
    }
}
