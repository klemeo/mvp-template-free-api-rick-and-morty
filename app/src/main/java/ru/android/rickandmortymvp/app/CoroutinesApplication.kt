package ru.android.rickandmortymvp.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoroutinesApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }


    private fun initKoin() {

        startKoin {

            androidContext(this@CoroutinesApplication)

            modules(modules)

        }

    }
}