package ru.android.rickandmortymvp.ui.locations

import io.reactivex.Single
import org.koin.core.inject
import ru.android.rickandmortymvp.app.models.data.location.Locations
import ru.android.rickandmortymvp.app.models.repository.Repository
import ru.android.rickandmortymvp.base.MvpPresenter
import ru.android.rickandmortymvp.ui.location.LocationScreen
import ru.android.rickandmortymvp.ui.mappers.LocationsToPresModelMapper

class Presenter(view: View) : MvpPresenter<View>(view) {

    private val repository: Repository by inject()

    private val locationsToPresModelMapper = LocationsToPresModelMapper()

    lateinit var locations: Locations

    override fun onCreate() {
        loadEpisodes()
    }

    fun showLocation(id: Int) {
        screensManager.showScreen(
            LocationScreen.newInstance(id)
        )
    }

    private fun loadEpisodes(page: Int? = null) {
        compositeDisposable.add(
            repository.getLocations(page)
                .flatMap { location ->
                    locations = location
                    Single.just(location)
                }
                .map {
                    locationsToPresModelMapper.map(it)
                }
                .compose(composer.single())
                .subscribe({ location ->
                    view?.refreshLocations(location)
                    view?.showLocations(true)
                }, {
                    //.
                })
        )
    }

}