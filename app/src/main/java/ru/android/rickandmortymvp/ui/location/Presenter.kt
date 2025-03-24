package ru.android.rickandmortymvp.ui.location

import org.koin.core.component.inject
import ru.android.rickandmortymvp.app.models.repository.Repository
import ru.android.rickandmortymvp.base.MvpPresenter
import ru.android.rickandmortymvp.ui.character.CharacterScreen
import ru.android.rickandmortymvp.ui.mappers.toMap

class Presenter(
    view: View,
    private val locationId: Int
) : MvpPresenter<View>(view) {

    private val repository: Repository by inject()

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
                .map {
                    it.toMap()
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