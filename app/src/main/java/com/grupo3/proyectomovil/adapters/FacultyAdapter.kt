package com.grupo3.proyectomovil.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grupo3.proyectomovil.databinding.ItemFacultyBinding
import com.grupo3.proyectomovil.models.Faculty

class FacultyAdapter(
    private val faculties: List<Faculty>,
    private val onFacultyClick: (Faculty) -> Unit
) : RecyclerView.Adapter<FacultyAdapter.FacultyViewHolder>() {

    private var filteredFaculties = faculties
    private val distances = mutableMapOf<Int, Float>()

    class FacultyViewHolder(val binding: ItemFacultyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacultyViewHolder {
        val binding = ItemFacultyBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FacultyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FacultyViewHolder, position: Int) {
        val faculty = filteredFaculties[position]
        holder.binding.apply {
            facultyName.text = faculty.name
            val distance = distances[faculty.id]
            facultyDescription.text = if (distance != null) {
                "${faculty.description}\nDistancia: ${String.format("%.2f", distance/1000)} km"
            } else {
                faculty.description
            }
            root.setOnClickListener { onFacultyClick(faculty) }
        }
    }

    override fun getItemCount() = filteredFaculties.size

    fun filter(query: String) {
        filteredFaculties = if (query.isEmpty()) {
            faculties
        } else {
            faculties.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

    fun updateDistance(facultyId: Int, distance: Float) {
        distances[facultyId] = distance
        notifyDataSetChanged()
    }
}