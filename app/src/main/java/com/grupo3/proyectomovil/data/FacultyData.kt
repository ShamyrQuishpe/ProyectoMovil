package com.grupo3.proyectomovil.data
import com.grupo3.proyectomovil.models.Faculty

//lista de todas las facultades de la EPN
object FacultyData {
    val faculties = listOf(
        Faculty(
            id = 1,
            name = "Facultad de Química",
            description = "Edificio 17 - Facultad de Ingeniería Química",
            latitude = -0.20972,
            longitude = -78.48877,
            modelPath = "models/FQuimica.glb",
            detectionRange = 20f
        )
    )
}