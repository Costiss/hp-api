package br.ufpr.hpapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.ufpr.hpapi.R
import br.ufpr.hpapi.model.Spell

class SpellAdapter(
    private val spells: List<Spell>,
    private val onSpellClick: (Spell) -> Unit
) : RecyclerView.Adapter<SpellAdapter.SpellViewHolder>() {

    inner class SpellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvSpellName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_spell, parent, false)
        return SpellViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpellViewHolder, position: Int) {
        val spell = spells[position]
        holder.tvName.text = spell.name
        holder.itemView.setOnClickListener { onSpellClick(spell) }
    }

    override fun getItemCount(): Int = spells.size
}
