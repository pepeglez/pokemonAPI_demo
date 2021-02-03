package com.example.pokemonapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.widget.NestedScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.pokemonapi.ItemDetailFragment.Companion.ARG_ITEM_ID
import com.example.pokemonapi.ItemDetailFragment.Companion.ARG_TWO_PANE
import com.example.pokemonapi.data.Pokemon
import com.example.pokemonapi.ui.PokemonAdapter
import com.example.pokemonapi.ui.PokemonViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    private lateinit var viewModel: PokemonViewModel
    private val adapter = PokemonAdapter()

    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
            twoPane = true
        }

        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory())
            .get(PokemonViewModel::class.java)

        initAdapter(findViewById(R.id.item_list))
        search()
    }

    private fun initAdapter(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter
    }

    private fun search() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchPokemons().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    fun showDetails(pokemon: Pokemon){

        if (twoPane){
            Log.d("ListActivity" , "Tablet device")
            val fragment = ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ITEM_ID, pokemon.getId())
                    putBoolean(ARG_TWO_PANE, twoPane)
                }
            }
            supportFragmentManager.beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()

        }else{
            val intent = Intent(this, ItemDetailActivity::class.java).apply {
                putExtra(ARG_ITEM_ID, pokemon.getId())
                putExtra(ARG_TWO_PANE, twoPane)
            }
            startActivity(intent)
        }
    }

}