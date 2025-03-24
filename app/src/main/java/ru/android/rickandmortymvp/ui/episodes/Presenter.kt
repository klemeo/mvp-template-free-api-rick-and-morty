package ru.android.rickandmortymvp.ui.episodes

import org.koin.core.component.inject
import ru.android.rickandmortymvp.app.models.repository.Repository
import ru.android.rickandmortymvp.base.MvpPresenter
import ru.android.rickandmortymvp.ui.episode.EpisodeScreen
import ru.android.rickandmortymvp.ui.mappers.toMap

class Presenter(view: View) : MvpPresenter<View>(view) {

    private val repository: Repository by inject()

    override fun onCreate() {
        loadEpisodes()
    }

    fun showEpisode(id: Int) {
        screensManager.showScreen(
            EpisodeScreen.newInstance(id)
        )
    }

    fun loadEpisodes(page: Int? = null) {
        compositeDisposable.add(
            repository.getEpisodes(page)
                .map {
                    it.toMap()
                }
                .compose(composer.single())
                .subscribe({ episode ->
                    view?.refreshEpisodes(episode)
                    view?.showEpisodes(true)
                }, {
                    //.
                })
        )
    }

}