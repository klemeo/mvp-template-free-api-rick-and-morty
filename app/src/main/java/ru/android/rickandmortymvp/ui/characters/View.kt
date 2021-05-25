package ru.android.rickandmortymvp.ui.characters

import ru.android.rickandmortymvp.app.models.data.character_pres_model.CharactersPresModel

interface View {

    fun refreshCharacters(characters: CharactersPresModel)

    fun showCharacters(animated: Boolean)

}