package com.example.wordsapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class WordAdapter(val letterId: String, val context: Context): RecyclerView.Adapter<WordAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val button: Button = view.findViewById(R.id.button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).
               inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = filteredWords[position].toString()
        holder.button.text = item
        holder.button.setOnClickListener {
            val queryUrl = "${WordListFragment.SEARCH_PREFIX}$item"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(queryUrl))
            context.startActivity(intent)
        }
    }

    private val words = context.resources.getStringArray(R.array.words)
    private val filteredWords = words.filter {
        it.startsWith(letterId, ignoreCase = true)
    }.shuffled().take(5).sorted()

    override fun getItemCount(): Int = filteredWords.size
}