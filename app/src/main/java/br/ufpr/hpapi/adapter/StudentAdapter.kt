package br.ufpr.hpapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.ufpr.hpapi.R
import br.ufpr.hpapi.model.Character
import com.bumptech.glide.Glide

class StudentAdapter(private val students: List<Character>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto: ImageView = itemView.findViewById(R.id.ivStudentPhoto)
        val tvName: TextView = itemView.findViewById(R.id.tvStudentName)
        val tvHouse: TextView = itemView.findViewById(R.id.tvStudentHouse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.tvName.text = student.name
        holder.tvHouse.text = student.house
        if (student.image.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(student.image)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(holder.ivPhoto)
        } else {
            holder.ivPhoto.setImageResource(android.R.drawable.ic_menu_gallery)
        }
    }

    override fun getItemCount(): Int = students.size
}
