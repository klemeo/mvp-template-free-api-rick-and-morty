package ru.android.rickandmortymvp.ui.characters

import io.reactivex.Single
import org.koin.core.inject
import ru.android.rickandmortymvp.app.models.data.character.Characters
import ru.android.rickandmortymvp.app.models.repository.Repository
import ru.android.rickandmortymvp.base.MvpPresenter
import ru.android.rickandmortymvp.ui.character.CharacterScreen
import ru.android.rickandmortymvp.ui.mappers.CharactersToPresModelMapper

class Presenter(view: View) : MvpPresenter<View>(view) {

    private val repository: Repository by inject()

    private val charactersToPresModelMapper = CharactersToPresModelMapper()

    lateinit var characters: Characters

    override fun onCreate() {
        loadCharacters()
    }

    fun showCharacter(id: Int) {
        screensManager.showScreen(
            CharacterScreen.newInstance(id)
        )
    }

    private fun loadCharacters(page: Int? = null) {
        compositeDisposable.add(
            repository.getCharacters(page)
                .flatMap { character ->
                    characters = character
                    Single.just(character)
                }
                .map {
                    charactersToPresModelMapper.map(it)
                }
                .compose(composer.single())
                .subscribe({ character ->
                    view?.refreshCharacters(character)
                    view?.showCharacters(true)
                }, {
                    //.
                })
        )
    }

}