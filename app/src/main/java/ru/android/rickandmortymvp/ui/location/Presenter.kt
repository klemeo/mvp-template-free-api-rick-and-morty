package ru.android.rickandmortymvp.ui.location

import io.reactivex.Single
import org.koin.core.inject
import ru.android.rickandmortymvp.app.models.data.location.Location
import ru.android.rickandmortymvp.app.models.repository.Repository
import ru.android.rickandmortymvp.base.MvpPresenter
import ru.android.rickandmortymvp.ui.character.CharacterScreen
import ru.android.rickandmortymvp.ui.mappers.LocationToPresModelMapper

class Presenter(
    view: View,
    private val locationId: Int
) : MvpPresenter<View>(view) {

    private val repository: Repository by inject()

    private val locationToPresModelMapper = LocationToPresModelMapper()

    lateinit var location: Location

    override fun onCreate() {
        loadLocation(locationId)
    }

    fun showCharacter(id: Int) {
        screensManager.showScreen(
            CharacterScreen.newInstance(id)
        )
        closeScreen()
    }

    private fun loadLocation(id: Int) {
        compositeDisposable.add(
            repository.getLocation(id)
                .flatMap {
                    location = it
                    Single.just(it)
                }
                .map {
                    locationToPresModelMapper.map(it)
                }
                .compose(composer.single())
                .subscribe({ location ->
                    view?.refreshLocation(location)
                    view?.showLocation(true)
                }, {
                    //.
                })
        )
    }

}