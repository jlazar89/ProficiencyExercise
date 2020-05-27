package com.arinspect.proficiencyexercise.di

import com.arinspect.proficiencyexercise.BuildConfig
import com.arinspect.proficiencyexercise.services.AboutCanadaService
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created Date: 27-05-2020
 * Purpose: KOIN Module provides retrofit related services which
 * provides the various required API Interfaces
 */
val retrofitModule = module {
    single { provideRetrofit(get(), get()) }
    single { provideAboutCanadaService(get()) }
}

/**
* Purpose: Function to provide retrofit instance.
*/

fun provideRetrofit(gson: Gson, httpClient: OkHttpClient) : Retrofit {

    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

// GET THE INSTANCE OF ABOUT CANADA SERVICE
fun provideAboutCanadaService(retrofit: Retrofit) : AboutCanadaService = retrofit.create(AboutCanadaService::class.java)