package com.arinspect.proficiencyexercise.di

/**
 * Main Koin DI component for Instrumentation Testing
 */
fun generateTestAppComponent(baseApi: String) = listOf(networkModule, retrofitModule, appModule)