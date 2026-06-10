package br.ufpr.hpapi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnCharacterById).setOnClickListener {
            startActivity(Intent(this, CharacterActivity::class.java))
        }
        findViewById<Button>(R.id.btnCharactersList).setOnClickListener {
            startActivity(Intent(this, CharactersListActivity::class.java))
        }
        findViewById<Button>(R.id.btnTeacher).setOnClickListener {
            startActivity(Intent(this, TeacherActivity::class.java))
        }
        findViewById<Button>(R.id.btnHouseStudents).setOnClickListener {
            startActivity(Intent(this, HouseStudentsActivity::class.java))
        }
        findViewById<Button>(R.id.btnSpells).setOnClickListener {
            startActivity(Intent(this, SpellsActivity::class.java))
        }
        findViewById<Button>(R.id.btnExit).setOnClickListener {
            finishAffinity()
        }
    }
}
