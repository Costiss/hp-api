package br.ufpr.hpapi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.ufpr.hpapi.adapter.SpellAdapter
import br.ufpr.hpapi.model.api.RetrofitClient
import kotlinx.coroutines.launch

class SpellsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spells)

        val rvSpells = findViewById<RecyclerView>(R.id.rvSpells)
        rvSpells.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            try {
                val spells = RetrofitClient.service.getAllSpells()
                rvSpells.adapter = SpellAdapter(spells) { spell ->
                    val intent = Intent(this@SpellsActivity, SpellDetailActivity::class.java).apply {
                        putExtra("spell_name", spell.name)
                        putExtra("spell_description", spell.description)
                    }
                    startActivity(intent)
                }
            } catch (e: Exception) {
                Toast.makeText(this@SpellsActivity, "Erro: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
