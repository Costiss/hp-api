package br.ufpr.hpapi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.ufpr.hpapi.model.api.RetrofitClient
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch

class CharacterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        val etId = findViewById<EditText>(R.id.etCharacterId)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val ivPhoto = findViewById<ImageView>(R.id.ivCharacterPhoto)
        val tvName = findViewById<TextView>(R.id.tvCharacterName)
        val tvSpecies = findViewById<TextView>(R.id.tvCharacterSpecies)
        val tvHouse = findViewById<TextView>(R.id.tvCharacterHouse)

        btnSearch.setOnClickListener {
            val id = etId.text.toString().trim()
            if (id.isEmpty()) {
                Toast.makeText(this, "Informe o ID do personagem", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            lifecycleScope.launch {
                try {
                    val results = RetrofitClient.service.getCharacterById(id)
                    if (results.isEmpty()) {
                        Toast.makeText(this@CharacterActivity, "Personagem não encontrado", Toast.LENGTH_SHORT).show()
                        return@launch
                    }
                    val character = results[0]
                    tvName.text = "Nome: ${character.name}"
                    tvSpecies.text = "Espécie: ${character.species}"
                    tvHouse.text = "Casa: ${character.house}"
                    if (character.image.isNotEmpty()) {
                        Glide.with(this@CharacterActivity)
                            .load(character.image)
                            .placeholder(android.R.drawable.ic_menu_gallery)
                            .into(ivPhoto)
                    } else {
                        ivPhoto.setImageResource(android.R.drawable.ic_menu_gallery)
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@CharacterActivity, "Erro: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
