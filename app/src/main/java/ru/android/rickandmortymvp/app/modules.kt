package ru.android.rickandmortymvp.app

import org.koin.dsl.module
import ru.android.rickandmortymvp.api.Api
import ru.android.rickandmortymvp.api.ApiImpl
import ru.android.rickandmortymvp.app.models.repository.Repository
import ru.android.rickandmortymvp.app.models.repository.RepositoryImpl
import ru.android.rickandmortymvp.base.SchedulerComposerFactory
import ru.android.rickandmortymvp.base.ScreensManager
import ru.android.rickandmortymvp.base.ScreensManagerImpl

private val allModule = module {

    //appModule
    factory {
        SchedulerComposerFactory.android()
    }

    single<ScreensManager> {
        ScreensManagerImpl()
    }
    //endregion

    //apiModule
    single<Api> {
        ApiImpl()
    }
    //endregion

    //dataModule
    single<Repository> {
        RepositoryImpl(
            api = get<Api>().getApi()
        )
    }
    //endregion

}

val modules = listOf(allModule)