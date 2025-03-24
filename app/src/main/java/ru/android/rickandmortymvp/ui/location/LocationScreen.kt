package ru.android.rickandmortymvp.ui.location

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.android.rickandmortymvp.R
import ru.android.rickandmortymvp.app.models.data.location_pres_model.LocationPresModel
import ru.android.rickandmortymvp.base.MvpFragment
import ru.android.rickandmortymvp.base.args
import ru.android.rickandmortymvp.ui.utils.*

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

    private var buttonBack: Button? = null
    private var linearLayout: LinearLayout? = null
    private var idTextView: TextView? = null
    private var nameTextView: TextView? = null
    private var dimensionTextView: TextView? = null
    private var createdTextView: TextView? = null
    private var recyclerView: RecyclerView? = null
    private var pbPost: ProgressBar? = null

    override fun initView(view: android.view.View, savedInstanceState: Bundle?) {
        with(view) {
            buttonBack = findViewById(R.id.buttonBack)
            linearLayout = findViewById(R.id.linearLayout)
            idTextView = findViewById(R.id.idTextView)
            nameTextView = findViewById(R.id.nameTextView)
            dimensionTextView = findViewById(R.id.dimensionTextView)
            createdTextView = findViewById(R.id.createdTextView)
            recyclerView = findViewById(R.id.recyclerView)
            pbPost = findViewById(R.id.pbPost)
        }
        recyclerView?.apply {
            layoutManager = GridLayoutManager(context, 5)
            adapter = locationAdapter
        }

        buttonBack?.setOnClickListener { presenter.closeScreen() }

    }

    override fun refreshLocation(location: LocationPresModel) {
        idTextView?.text = location.id.toString()
        nameTextView?.text = location.name
        createdTextView?.text = location.created
        location.residents?.let { locationAdapter.setData(it) }
    }

    override fun showLocation(animated: Boolean) {
        when (animated) {
            true -> {
                linearLayout?.visible()
                pbPost?.gone()
            }

            else -> {
                linearLayout?.gone()
                pbPost?.visible()
            }
        }
    }
}