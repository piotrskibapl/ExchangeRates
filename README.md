# Project description
Exchange Rates is a simple application that displays current and historical PLN exchange rates.

The application consists of two different screens (Activities). Upon launching, the User is presented with `RateListActivity`, which displays a list of currency exchange rates. Tapping on any of them navigates the User to `RateDetailsActivity` with historical (2 weeks) exchange rates for that currency.

## Tech Stack
The app was created in Kotlin using MVVM + Clean Architecture.

Key libraries:
 - Jetpack Compose
 - Hilt
 - RxJava3

Unit Tests:
 - JUnit5
 - MockK
 - kluent

## API
The application utilizes [NBP Web API](https://api.nbp.pl/) to fetch currency exchange rate information.