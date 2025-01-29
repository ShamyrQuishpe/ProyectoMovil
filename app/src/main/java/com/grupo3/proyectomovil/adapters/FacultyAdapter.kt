package com.grupo3.proyectomovil.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grupo3.proyectomovil.databinding.ItemFacultyBinding
import com.grupo3.proyectomovil.models.Faculty

class FacultyAdapter(
    private val faculties: List<Faculty>, //Lista las facultades para pasarlas al adaptador
    private val onFacultyClick: (Faculty) -> Unit //Callback de ejecucion de items
) : RecyclerView.Adapter<FacultyAdapter.FacultyViewHolder>() {

    //Lista las facultades por busqueda
    private var filteredFaculties = faculties

    //Almacena las distancias calculadas para cada facultad
    private val distances = mutableMapOf<Int, Float>()

    //Maneja los elementos de vista
    class FacultyViewHolder(val binding: ItemFacultyBinding) : RecyclerView.ViewHolder(binding.root)

    // Creacion del ViewHolder para cada item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacultyViewHolder {
        val binding = ItemFacultyBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FacultyViewHolder(binding) //Devuelve un nuevo ViewHolder
    }

    //Vincula los datos de la facultad con la interfaz del ViewHolder
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

    //Devuelve numero de elementos en la lista
    override fun getItemCount() = filteredFaculties.size

    //Filtra la lista de facultades segun la consulta de busqueda
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

    //Actualiza la distancia de una facultad especifica y notifica al adaptador
    fun updateDistance(facultyId: Int, distance: Float) {
        distances[facultyId] = distance
        notifyDataSetChanged()
    }

}