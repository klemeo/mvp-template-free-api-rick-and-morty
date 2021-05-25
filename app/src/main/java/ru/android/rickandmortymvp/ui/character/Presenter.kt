package ru.android.rickandmortymvp.ui.character

import io.reactivex.Single
import org.koin.core.inject
import ru.android.rickandmortymvp.app.models.data.character.Character
import ru.android.rickandmortymvp.app.models.repository.Repository
import ru.android.rickandmortymvp.base.MvpPresenter
import ru.android.rickandmortymvp.ui.episode.EpisodeScreen
import ru.android.rickandmortymvp.ui.mappers.CharacterToPresModelMapper

class Presenter(
    view: View,
    private val characterId: Int
) : MvpPresenter<View>(view) {

    private val repository: Repository by inject()

    private val characterToPresModelMapper = CharacterToPresModelMapper()

    lateinit var character: Character

    override fun onCreate() {
        loadCharacter(characterId)
    }

    fun showEpisode(id: Int) {
        screensManager.showScreen(
            EpisodeScreen.newInstance(id)
        )
    }

    private fun loadCharacter(id: Int) {
        compositeDisposable.add(
            repository.getCharacter(id)
                .flatMap {
                    character = it
                    Single.just(it)
                }
                .map {
                    characterToPresModelMapper.map(it)
                }
                .compose(composer.single())
                .subscribe({ episode ->
                    view?.refreshCharacter(episode)
                    view?.showCharacter(true)
                }, {
                    //.
                })
        )
    }

}