package ru.android.rickandmortymvp.ui.location

import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_location.*
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.app.models.data.location_pres_model.LocationPresModel
import ru.android.rickandmortymvp.base.MvpFragment
import ru.android.rickandmortymvp.base.args
import ru.android.rickandmortymvp.ui.utils.gone
import ru.android.rickandmortymvp.ui.utils.visible

class LocationScreen : MvpFragment<Presenter>(), View {

    companion object {

        fun newInstance(
            location: Int,
        ) = LocationScreen().args {
            putInt(ARG_LOCATION_ID, location)
        }

        private const val ARG_LOCATION_ID = "ARG_LOCATION_ID"
    }

    override val presenter by lazy {
        Presenter(
            view = this,
            locationId = requireArguments().getInt(ARG_LOCATION_ID)
        )
    }

    override val layout: Int = R.layout.fragment_location

    private val locationAdapter by lazy {
        LocationAdapter().apply {
            onClick = { presenter.showCharacter(it) }
        }
    }

    override fun initView(view: android.view.View, savedInstanceState: Bundle?) {
        with(recyclerView) {
            layoutManager = GridLayoutManager(context, 5)
            adapter = locationAdapter
        }

        buttonBack.setOnClickListener { presenter.closeScreen() }

    }

    override fun refreshLocation(location: LocationPresModel) {
        idTextView.text = location.id.toString()
        nameTextView.text = location.name
        createdTextView.text = location.created
        location.residents?.let { locationAdapter.setData(it) }
    }

    override fun showLocation(animated: Boolean) {
        when (animated) {
            true -> {
                linearLayout.visible()
                pbPost.gone()
            }
            else -> {
                linearLayout.gone()
                pbPost.visible()
            }
        }
    }
}