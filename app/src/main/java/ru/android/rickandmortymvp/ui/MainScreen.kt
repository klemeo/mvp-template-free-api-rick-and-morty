package ru.android.rickandmortymvp.ui

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_main_screen.*
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.base.BaseFragment
import ru.android.rickandmortymvp.ui.characters.CharactersScreen
import ru.android.rickandmortymvp.ui.episodes.EpisodesScreen
import ru.android.rickandmortymvp.ui.locations.LocationsScreen

class MainScreen : BaseFragment(R.layout.fragment_main_screen) {

    override fun initView(view: View, savedInstanceState: Bundle?) {

        charactersButton.setOnClickListener {
            screensManager.showScreen(CharactersScreen())
        }
        locationsButton.setOnClickListener {
            screensManager.showScreen(LocationsScreen())
        }
        episodesButton.setOnClickListener {
            screensManager.showScreen(EpisodesScreen())
        }

    }
}