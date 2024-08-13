package pl.piotrskiba.exchangerates.base.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.piotrskiba.exchangerates.base.rx.SchedulersFacade
import pl.piotrskiba.exchangerates.base.rx.SchedulersProvider

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindSchedulersProvider(schedulersFacade: SchedulersFacade): SchedulersProvider
}
