package ru.android.rickandmortymvp.ui.locations

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_locations.*
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.app.models.data.location_pres_model.LocationsPresModel
import ru.android.rickandmortymvp.base.MvpFragment
import ru.android.rickandmortymvp.ui.utils.gone
import ru.android.rickandmortymvp.ui.utils.pageLocations
import ru.android.rickandmortymvp.ui.utils.visible

class LocationsScreen : MvpFragment<Presenter>(), View {

    override val presenter by lazy {
        Presenter(
            view = this
        )
    }

    private var prevPage: Int? = null

    private var nextPage: Int? = null

    override val layout: Int = R.layout.fragment_locations

    private val locationsAdapter by lazy {
        LocationsAdapter().apply {
            onClick = { it.id?.let { locationId -> presenter.showLocation(locationId) } }
        }
    }

    override fun initView(view: android.view.View, savedInstanceState: Bundle?) {
        with(recyclerView) {
            layoutManager = GridLayoutManager(context, 1)
            adapter = locationsAdapter
        }

        backButton.gone()
        nextButton.gone()

        buttonBack.setOnClickListener { presenter.closeScreen() }

        nextButton.setOnClickListener {
            presenter.loadLocations(nextPage)
        }

        backButton.setOnClickListener {
            presenter.loadLocations(prevPage)
        }

    }

    override fun refreshLocations(locations: LocationsPresModel) {
        locations.results?.let { locationsAdapter.setData(it) }
        nextPage = if (locations.info?.next != null) locations.info.next
            .pageLocations() else null
        when {
            nextPage != null -> nextButton.visible()
            nextPage == null -> nextButton.gone()
        }
        prevPage = if (locations.info?.prev != null) locations.info.prev.toString()
            .pageLocations() else null
        when {
            prevPage != null -> backButton.visible()
            prevPage == null -> backButton.gone()
        }
    }

    override fun showLocations(animated: Boolean) {
        when (animated) {
            true -> {
                recyclerView.visible()
                pbPost.gone()
            }
            else -> {
                recyclerView.gone()
                pbPost.visible()
            }
        }
    }
}