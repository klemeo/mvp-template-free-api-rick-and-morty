package ru.android.rickandmortymvp.ui.character

import org.koin.core.component.inject
import ru.android.rickandmortymvp.app.models.repository.Repository
import ru.android.rickandmortymvp.base.MvpPresenter
import ru.android.rickandmortymvp.ui.episode.EpisodeScreen
import ru.android.rickandmortymvp.ui.mappers.toMap

class Presenter(
    view: View,
    private val characterId: Int
) : MvpPresenter<View>(view) {

    private val repository: Repository by inject()

    override fun onCreate() {
        loadCharacter(characterId)
    }

    fun showEpisode(id: Int) {
        screensManager.showScreen(
            EpisodeScreen.newInstance(id)
        )
        closeScreen()
    }

    private fun loadCharacter(id: Int) {
        compositeDisposable.add(
            repository.getCharacter(id)
                .map {
                    it.toMap()
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