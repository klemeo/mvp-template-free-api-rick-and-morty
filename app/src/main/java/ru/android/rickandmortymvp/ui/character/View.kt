package ru.android.rickandmortymvp.ui.character

import ru.android.rickandmortymvp.app.models.data.character_pres_model.CharacterPresModel


interface View {

    fun refreshCharacter(character: CharacterPresModel)

    fun showCharacter(animated: Boolean)

}