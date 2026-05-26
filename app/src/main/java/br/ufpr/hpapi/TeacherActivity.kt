package br.ufpr.hpapi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.ufpr.hpapi.model.api.RetrofitClient
import kotlinx.coroutines.launch

class TeacherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)

        val etName = findViewById<EditText>(R.id.etTeacherName)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val tvName = findViewById<TextView>(R.id.tvTeacherName)
        val tvAltNames = findViewById<TextView>(R.id.tvTeacherAlternateNames)
        val tvSpecies = findViewById<TextView>(R.id.tvTeacherSpecies)
        val tvHouse = findViewById<TextView>(R.id.tvTeacherHouse)

        btnSearch.setOnClickListener {
            val query = etName.text.toString().trim()
            if (query.isEmpty()) {
                Toast.makeText(this, "Informe o nome do professor", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            lifecycleScope.launch {
                try {
                    val staff = RetrofitClient.service.getAllStaff()
                    val teacher = staff.firstOrNull { it.name.contains(query, ignoreCase = true) }
                    if (teacher == null) {
                        Toast.makeText(this@TeacherActivity, "Professor não encontrado", Toast.LENGTH_SHORT).show()
                        tvName.text = ""
                        tvAltNames.text = ""
                        tvSpecies.text = ""
                        tvHouse.text = ""
                        return@launch
                    }
                    tvName.text = "Nome: ${teacher.name}"
                    tvAltNames.text = "Outros nomes: ${teacher.alternateNames.joinToString(", ").ifEmpty { "—" }}"
                    tvSpecies.text = "Espécie: ${teacher.species}"
                    tvHouse.text = "Casa: ${teacher.house}"
                } catch (e: Exception) {
                    Toast.makeText(this@TeacherActivity, "Erro: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
