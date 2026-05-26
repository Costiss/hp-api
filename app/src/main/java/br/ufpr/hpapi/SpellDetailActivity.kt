package br.ufpr.hpapi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SpellDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spell_detail)

        val tvName = findViewById<TextView>(R.id.tvSpellName)
        val tvDescription = findViewById<TextView>(R.id.tvSpellDescription)

        tvName.text = intent.getStringExtra("spell_name") ?: ""
        tvDescription.text = intent.getStringExtra("spell_description") ?: ""
    }
}
