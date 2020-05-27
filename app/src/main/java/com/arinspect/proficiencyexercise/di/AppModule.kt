package com.arinspect.proficiencyexercise.di

import com.arinspect.proficiencyexercise.respository.AboutCanadaRepository
import com.arinspect.proficiencyexercise.ui.list.AboutCanadaViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created Date: 27-05-2020
 * Purpose: KOIN module to manage app level dependencies.
 */
val appModule = module {

    /**
     *  OTHER CLASSES
     */

    /**
     *  Here we list all the repository which are needed.
     */
    single { AboutCanadaRepository(get()) }



    /**
     *  Here we list all the view models which are injected.
     */
    viewModel { AboutCanadaViewModel(get()) }


}
