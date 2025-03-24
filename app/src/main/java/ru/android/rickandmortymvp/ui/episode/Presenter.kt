package ru.android.rickandmortymvp.ui.episode

import org.koin.core.component.inject
import ru.android.rickandmortymvp.app.models.repository.Repository
import ru.android.rickandmortymvp.base.MvpPresenter
import ru.android.rickandmortymvp.ui.character.CharacterScreen
import ru.android.rickandmortymvp.ui.mappers.toMap

class Presenter(
    view: View,
    private val episodeId: Int
) : MvpPresenter<View>(view) {

    private val repository: Repository by inject()

    override fun onCreate() {
        loadEpisode(episodeId)
    }

    fun showCharacter(id: Int) {
        screensManager.showScreen(
            CharacterScreen.newInstance(id)
        )
        closeScreen()
    }

    private fun loadEpisode(id: Int) {
        compositeDisposable.add(
            repository.getEpisode(id)
                .map {
                    it.toMap()
                }
                .compose(composer.single())
                .subscribe({ episode ->
                    view?.refreshEpisode(episode)
                    view?.showEpisode(true)
                }, {
                    //.
                })
        )
    }

}