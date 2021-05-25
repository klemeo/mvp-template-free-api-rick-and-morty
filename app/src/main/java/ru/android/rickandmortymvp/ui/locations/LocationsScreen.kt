package ru.android.rickandmortymvp.ui.locations

import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_locations.*
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.app.models.data.location_pres_model.LocationsPresModel
import ru.android.rickandmortymvp.base.MvpFragment

class LocationsScreen : MvpFragment<Presenter>(), View {

    override val presenter by lazy {
        Presenter(
            view = this
        )
    }

    override val layout: Int = R.layout.fragment_locations

    private val locationsAdapter by lazy {
        LocationsAdapter().apply {
            onClick = { }
        }
    }

    override fun initView(view: android.view.View, savedInstanceState: Bundle?) {
        with(recyclerView) {
            layoutManager = GridLayoutManager(context, 1)
            adapter = locationsAdapter
        }

        buttonBack.setOnClickListener { presenter.closeScreen() }

    }

    override fun refreshLocations(locations: LocationsPresModel) {
        locations.results?.let { locationsAdapter.setData(it) }
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