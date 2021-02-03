package com.example.pokemonapi

import DetailApiResponse
import Stats
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
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
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.pokemonapi.api.PokeApiService
import com.example.pokemonapi.customsviews.StatsView
import com.example.pokemonapi.data.Pokemon
import com.example.pokemonapi.ui.PokemonDetailViewModel
import com.example.pokemonapi.ui.PokemonViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: Pokemon? = null
    private var pokeId: String? = null

    private lateinit var viewModel: PokemonDetailViewModel

    private var searchJob: Job? = null

    private var tvName: TextView? = null
    private var llStatsContainer: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {

                pokeId = it.getString(ARG_ITEM_ID)

                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                //item = DummyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = "..."
            }
        }

        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory())
                .get(PokemonDetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        // Show the dummy content as text in a TextView.
        item = Pokemon("Nombre", "url" )

        settingInterface(rootView)
        getDetails(pokeId!!)
        return rootView
    }

    private fun settingInterface (rootView: View){
        llStatsContainer = rootView.findViewById(R.id.ll_stats_container)
    }

    private fun getDetails(pokeId: String) {
        viewModel.getDetails(pokeId)?.observe(viewLifecycleOwner, Observer {
            activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = it.name
            constructStatsDetails(it.stats)
        })
    }

    private fun constructStatsDetails (listStats: List<Stats>){
        for (stats in listStats){
            llStatsContainer!!.addView(StatsView(activity as Context, stats.stat.name
                    + ": ${stats.base_stat}", stats.base_stat ))
        }
    }

    companion object {

        const val ARG_ITEM_ID = "item_id"
    }
}