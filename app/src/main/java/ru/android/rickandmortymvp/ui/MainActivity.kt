package ru.android.rickandmortymvp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener
import androidx.core.view.WindowInsetsCompat
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

        enableEdgeToEdge()

        setOnApplyWindowInsetsListener(window.decorView) { v: View, insets: WindowInsetsCompat ->
            val systemBars: Insets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }

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