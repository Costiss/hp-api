package br.ufpr.hpapi

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.ufpr.hpapi.adapter.StudentAdapter
import br.ufpr.hpapi.model.api.RetrofitClient
import kotlinx.coroutines.launch

class HouseStudentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_house_students)

        val rgHouses = findViewById<RadioGroup>(R.id.rgHouses)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val rvStudents = findViewById<RecyclerView>(R.id.rvStudents)

        rvStudents.layoutManager = LinearLayoutManager(this)

        btnSearch.setOnClickListener {
            val house = when (rgHouses.checkedRadioButtonId) {
                R.id.rbGryffindor -> "Gryffindor"
                R.id.rbSlytherin -> "Slytherin"
                R.id.rbHufflepuff -> "Hufflepuff"
                R.id.rbRavenclaw -> "Ravenclaw"
                else -> null
            }
            if (house == null) {
                Toast.makeText(this, "Selecione uma casa", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            lifecycleScope.launch {
                try {
                    val students = RetrofitClient.service.getStudentsByHouse(house)
                    rvStudents.adapter = StudentAdapter(students)
                } catch (e: Exception) {
                    Toast.makeText(this@HouseStudentsActivity, "Erro: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
