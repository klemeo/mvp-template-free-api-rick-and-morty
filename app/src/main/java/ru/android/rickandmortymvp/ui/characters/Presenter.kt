package ru.android.rickandmortymvp.ui.characters

import org.koin.core.component.inject
import ru.android.rickandmortymvp.app.models.repository.Repository
import ru.android.rickandmortymvp.base.MvpPresenter
import ru.android.rickandmortymvp.ui.character.CharacterScreen
import ru.android.rickandmortymvp.ui.mappers.toMap

class Presenter(view: View) : MvpPresenter<View>(view) {

    private val repository: Repository by inject()

    override fun onCreate() {
        loadCharacters()
    }

    fun showCharacter(id: Int) {
        screensManager.showScreen(
            CharacterScreen.newInstance(id)
        )
    }

    fun loadCharacters(page: Int? = null) {
        compositeDisposable.add(
            repository.getCharacters(page)
                .map {
                    it.toMap()
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