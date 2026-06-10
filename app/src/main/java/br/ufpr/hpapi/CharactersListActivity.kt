package br.ufpr.hpapi

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.ufpr.hpapi.adapter.CharacterAdapter
import br.ufpr.hpapi.model.api.RetrofitClient
import kotlinx.coroutines.launch

class CharactersListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters_list)

        val rvCharacters = findViewById<RecyclerView>(R.id.rvCharacters)
        rvCharacters.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            try {
                val characters = RetrofitClient.service.getAllCharacters()
                rvCharacters.adapter = CharacterAdapter(characters) { character ->
                    copyToClipboard(character.id)
                }
            } catch (e: Exception) {
                Toast.makeText(this@CharactersListActivity, "Erro: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun copyToClipboard(id: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText("character_id", id))
        Toast.makeText(this, "ID copiado: $id", Toast.LENGTH_SHORT).show()
    }
}
