package ru.android.rickandmortymvp.ui.locations

import org.koin.core.component.inject
import ru.android.rickandmortymvp.app.models.repository.Repository
import ru.android.rickandmortymvp.base.MvpPresenter
import ru.android.rickandmortymvp.ui.location.LocationScreen
import ru.android.rickandmortymvp.ui.mappers.toMap

class Presenter(view: View) : MvpPresenter<View>(view) {

    private val repository: Repository by inject()

    override fun onCreate() {
        loadLocations()
    }

    fun showLocation(id: Int) {
        screensManager.showScreen(
            LocationScreen.newInstance(id)
        )
    }

    fun loadLocations(page: Int? = null) {
        compositeDisposable.add(
            repository.getLocations(page)
                .map {
                    it.toMap()
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