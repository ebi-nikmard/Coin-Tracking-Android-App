# Coin Tracking Android App

## Overview

This Android app is a cryptocurrency tracking application developed for a technical test. The app allows users to view a list of cryptocurrencies, their details, and historical price charts. It utilizes an open-source cryptocurrency API for fetching data and showcases best engineering practices.

## Features

- **Main Activity:** Lists cryptocurrencies with avatars, names, symbols, and prices. Each item is clickable.
- **Coin Details Activity:** Displays a historical price chart for selected cryptocurrencies.

## Technologies Used

- **Language:** Kotlin
- **Architecture:** MVVM (Model-View-ViewModel)
- **Networking:** Retrofit
- **Asynchronous Programming:** Kotlin Coroutines
- **Charting Library:** MPAndroidChart
- **Android Architecture Components:**
  - ViewModel
  - LiveData
- **Testing:**
  - JUnit
  - Mockito
- **Open-source API:** CoinStats API (https://openapi.coinstats.app/)

## Project Structure

The project follows a modular MVVM architecture for clear separation of concerns. Key components include:

- **model:** Contains data models used in the application.

- **network:** Handles networking-related components, including API services.

- **repository:** Manages data sources and implements the repository pattern.

- **ui:**
  - **adapter:** Contains RecyclerView adapters or other adapters used in the user interface.
  - **viewmodel:** Includes ViewModel classes responsible for managing UI-related data and handling user interactions.

## Setup

To run the application:

1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run the app on an Android emulator or physical device.

## Additional Notes

- Ensure internet permission is added in the AndroidManifest.xml file:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## Acknowledgments

This project is developed for a technical test and focuses on demonstrating best engineering practices, including performance, readability, maintainability, testability, scalability, and simplicity.

Feel free to explore, customize, and use the code for educational purposes.
