package com.niv_angert.anydo.koin

import com.niv_angert.anydo.data.repositories.GroceriesRepository
import com.niv_angert.anydo.data.repositories.GroceriesRepositoryImpl
import com.niv_angert.anydo.data.sources.remote.RemoteHelper
import com.niv_angert.anydo.data.sources.remote.RemoteHelperImpl
import com.niv_angert.anydo.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 *Created by Niv Angert on 02/08/2021
 **/
val appModule = module {

    // Data Sources --------------------------------------------------------------------------------

    single<RemoteHelper> {
        RemoteHelperImpl()
    }

    // Repositories --------------------------------------------------------------------------------

    factory<GroceriesRepository> { GroceriesRepositoryImpl(get()) }

    // UseCases ------------------------------------------------------------------------------------

    //todo Domain UseCases!!

    // Activity ViewModels -------------------------------------------------------------------------

    viewModel(named(KoinConsts.MAIN_VIEW_MODEL)) {
        MainViewModel(get())
    }
}