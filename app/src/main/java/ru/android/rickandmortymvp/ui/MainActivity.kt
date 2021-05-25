package ru.android.rickandmortymvp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.manager.SupportRequestManagerFragment
import org.koin.android.ext.android.inject
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.base.BackPressedHandler
import ru.android.rickandmortymvp.base.ScreensManager
import ru.android.rickandmortymvp.base.ScreensManagerImpl

class MainActivity : AppCompatActivity() {

    private val screensManager: ScreensManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (screensManager as ScreensManagerImpl).init(this, R.id.screensContainer)

        screensManager.showScreen(MainScreen())

    }

    override fun onBackPressed() {
        val fragmentList = supportFragmentManager.fragments

        if (fragmentList.isNotEmpty()) {
            val topFragment = fragmentList.last { it !is SupportRequestManagerFragment }

            if (topFragment is BackPressedHandler) {
                if (!topFragment.onBackPressed()) {
                    super.onBackPressed()
                }

            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }
}