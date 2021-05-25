package ru.android.rickandmortymvp.ui.episode

import io.reactivex.Single
import org.koin.core.inject
import ru.android.rickandmortymvp.app.models.data.episode.Episode
import ru.android.rickandmortymvp.app.models.repository.Repository
import ru.android.rickandmortymvp.base.MvpPresenter
import ru.android.rickandmortymvp.ui.character.CharacterScreen
import ru.android.rickandmortymvp.ui.mappers.EpisodeToPresModelMapper

class Presenter(
    view: View,
    private val episodeId: Int
) : MvpPresenter<View>(view) {

    private val repository: Repository by inject()

    private val episodeToPresModelMapper = EpisodeToPresModelMapper()

    lateinit var episode: Episode

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
                .flatMap {
                    episode = it
                    Single.just(it)
                }
                .map {
                    episodeToPresModelMapper.map(it)
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