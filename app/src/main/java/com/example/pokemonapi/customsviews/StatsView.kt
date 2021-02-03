package com.example.pokemonapi.customsviews

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.example.pokemonapi.R

class StatsView(context: Context,name: String, stat: Int): LinearLayout(context) {

    init {
        inflate(context, R.layout.item_stats, this)

        val progress: ProgressBar = findViewById(R.id.progressBar)
        val textView: TextView = findViewById(R.id.tc_stats_value)

        progress.setProgress(stat , true)
        textView.text = name
    }
}