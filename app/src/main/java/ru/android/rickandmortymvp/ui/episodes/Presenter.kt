package ru.android.rickandmortymvp.ui.episodes

import io.reactivex.Single
import org.koin.core.inject
import ru.android.rickandmortymvp.app.models.data.episode.Episodes
import ru.android.rickandmortymvp.app.models.repository.Repository
import ru.android.rickandmortymvp.base.MvpPresenter
import ru.android.rickandmortymvp.ui.episode.EpisodeScreen
import ru.android.rickandmortymvp.ui.mappers.EpisodesToPresModelMapper

class Presenter(view: View) : MvpPresenter<View>(view) {

    private val repository: Repository by inject()

    private val episodesToPresModelMapper = EpisodesToPresModelMapper()

    lateinit var episodes: Episodes

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
                .flatMap { episode ->
                    episodes = episode
                    Single.just(episode)
                }
                .map {
                    episodesToPresModelMapper.map(it)
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