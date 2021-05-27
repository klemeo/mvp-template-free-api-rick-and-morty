package ru.android.rickandmortymvp.ui.locations

import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_characters.*
import kotlinx.android.synthetic.main.fragment_locations.*
import kotlinx.android.synthetic.main.fragment_locations.backButton
import kotlinx.android.synthetic.main.fragment_locations.buttonBack
import kotlinx.android.synthetic.main.fragment_locations.nextButton
import kotlinx.android.synthetic.main.fragment_locations.pbPost
import kotlinx.android.synthetic.main.fragment_locations.recyclerView
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.app.models.data.location_pres_model.LocationsPresModel
import ru.android.rickandmortymvp.base.MvpFragment

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
        nextPage = locations.info?.next?.replace(
            "https://rickandmortyapi.com/api/location?page=",
            ""
        )?.toInt()
        prevPage =
            if (locations.info?.prev != null) locations.info.prev.toString()
                .replace("https://rickandmortyapi.com/api/location?page=", "")
                .toInt() else null
    }

    override fun showLocations(animated: Boolean) {
        when (animated) {
            true -> {
                recyclerView.isVisible = true
                pbPost.isGone = true
            }
            else -> {
                recyclerView.isGone = true
                pbPost.isVisible = true
            }
        }
    }
}