package ru.android.rickandmortymvp.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.base.BaseFragment
import ru.android.rickandmortymvp.ui.characters.CharactersScreen
import ru.android.rickandmortymvp.ui.episodes.EpisodesScreen
import ru.android.rickandmortymvp.ui.locations.LocationsScreen

class MainScreen : BaseFragment(R.layout.fragment_main_screen) {


    override fun initView(view: View, savedInstanceState: Bundle?) {
        val charactersButton = view.findViewById<Button>(R.id.charactersButton)
        val locationsButton = view.findViewById<Button>(R.id.locationsButton)
        val episodesButton = view.findViewById<Button>(R.id.episodesButton)

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