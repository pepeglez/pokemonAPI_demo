package com.example.pokemonapi

import DetailApiResponse
import Stats
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokemonapi.api.PokeApiService.Companion.getImageUrl
import com.example.pokemonapi.customsviews.StatsView
import com.example.pokemonapi.ui.PokemonDetailViewModel

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    private lateinit var pokeId: String

    private var twoPane: Boolean = false

    private lateinit var viewModel: PokemonDetailViewModel

    private var tvName: TextView? = null
    private var imgHeader: ImageView? = null
    private var llStatsContainer: LinearLayout? = null
    private var llHeaderContainer: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                pokeId = it.getString(ARG_ITEM_ID)!!
                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = "..."
            }
            twoPane = it.getBoolean(ARG_TWO_PANE)
        }

        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory())
                .get(PokemonDetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        settingInterface(rootView)
        getDetails(pokeId)
        return rootView
    }

    private fun settingInterface (rootView: View){
        llStatsContainer = rootView.findViewById(R.id.ll_stats_container)

        if (!twoPane){
            rootView.findViewById<LinearLayout>(R.id.ll_header_container).visibility = View.GONE
            return
        }

        tvName = rootView.findViewById(R.id.tv_name_detail)
        imgHeader = rootView.findViewById(R.id.img_pokemon_detail)
    }

    private fun getDetails(pokeId: String) {
        viewModel.getDetails(pokeId)?.observe(viewLifecycleOwner, Observer {
            activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = it.name

            if (twoPane) setHeaderInfo(it)
            constructStatsDetails(it.stats)
        })
    }

    private fun setHeaderInfo (details: DetailApiResponse){
        tvName?.text = details.name

        Glide.with(this)
                .load(getImageUrl(pokeId))
                .centerInside()
                .error(R.mipmap.ic_launcher_round)
                .fallback(R.mipmap.ic_launcher_round)
                .into(imgHeader!!)

    }

    private fun constructStatsDetails (listStats: List<Stats>){
        for (stats in listStats){
            llStatsContainer!!.addView(StatsView(activity as Context, stats.stat.name
                    + ": ${stats.base_stat}", stats.base_stat ))
        }
    }

    companion object {

        const val ARG_ITEM_ID = "item_id"
        const val ARG_TWO_PANE = "two_pane"
    }
}