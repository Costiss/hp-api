package br.ufpr.hpapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.ufpr.hpapi.R
import br.ufpr.hpapi.model.Character
import com.google.android.material.button.MaterialButton

class CharacterAdapter(
    private val characters: List<Character>,
    private val onCopyId: (Character) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvCharacterName)
        val tvId: TextView = itemView.findViewById(R.id.tvCharacterId)
        val btnCopyId: MaterialButton = itemView.findViewById(R.id.btnCopyId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.tvName.text = character.name.ifEmpty { "(sem nome)" }
        holder.tvId.text = "ID: ${character.id}"
        holder.btnCopyId.setOnClickListener { onCopyId(character) }
    }

    override fun getItemCount(): Int = characters.size
}
