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
        ),
        Faculty(
            id = 2,
            name = "Facultad de Sistemas",
            description = "Facultad de Ingeniería en Sistemas",
            latitude = -0.21020,
            longitude = -78.48895,
            modelPath = "models/FQuimica.glb", // Cambiar el modelo
            detectionRange = 20f
        ),
        Faculty(
            id = 3,
            name = "Facultad de Geología y Petroleos",
            description = "Facultad de Geología y Petroleos",
            latitude = -0.20939,
            longitude = -78.48954,
            modelPath = "models/FQuimica.glb", //Cambiar el modelo
            detectionRange = 20f
        ),

    )
}