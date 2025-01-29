package com.grupo3.proyectomovil.data
import com.grupo3.proyectomovil.models.Faculty

//lista de todas las facultades de la EPN
object FacultyData {
    val faculties = listOf(
        Faculty(
            id = 1,
            name = "Facultad de Química",
            description = "Edificio 18 - Facultad de Ingeniería Química y Agroindustria",
            latitude = -0.20972,
            longitude = -78.48877,
            modelPath = "models/FQuimica.glb",
            detectionRange = 20f
        ),
        Faculty(
            id = 2,
            name = "Facultad de Sistemas",
            description = "Edificio 20 - Facultad de Ingeniería en Sistemas",
            latitude = -0.21020,
            longitude = -78.48895,
            modelPath = "models/FQuimica.glb", // Cambiar el modelo
            detectionRange = 20f
        ),
        Faculty(
            id = 3,
            name = "Facultad de Ciencias",
            description = "Edificio 12 - Facultad de Ciencias",
            latitude = -0.20939,
            longitude = -78.48954,
            modelPath = "models/FQuimica.glb", //Cambiar el modelo
            detectionRange = 20f
        ),
        Faculty(
            id = 4,
            name = "Facultad de Ciencias Administrativas",
            description = "Edificio 25 - Facultad de Ingeniería en Sistemas",
            latitude = -0.21020,
            longitude = -78.48895,
            modelPath = "models/FQuimica.glb", // Cambiar el modelo
            detectionRange = 20f
        ),
        Faculty(
            id = 5,
            name = "Facultad de Ingeniería Civil y Ambiental",
            description = "Edificio 6 - Facultad de Ingeniería Civil y Ambiental",
            latitude = -0.20939,
            longitude = -78.48954,
            modelPath = "models/FQuimica.glb", //Cambiar el modelo
            detectionRange = 20f
        ),
        Faculty(
            id = 6,
            name = "Facultad de Ingeniería Mecánica ",
            description = "Edificio 15 - Facultad de Ingeniería Mecánica",
            latitude = -0.20939,
            longitude = -78.48954,
            modelPath = "models/FQuimica.glb", //Cambiar el modelo
            detectionRange = 20f
        ),
        Faculty(
            id = 7,
            name = "Facultad de Ingeniería Eléctrica y Electrónica",
            description = "Edificio 16 - Facultad de Ingeniería Eléctrica y Electrónica",
            latitude = -0.20939,
            longitude = -78.48954,
            modelPath = "models/FQuimica.glb", //Cambiar el modelo
            detectionRange = 20f
        ),
        Faculty(
            id = 8,
            name = "Facultad de Geología y Petróleos",
            description = "Edificio 13 - Facultad de Geología y Petróleos",
            latitude = -0.20939,
            longitude = -78.48954,
            modelPath = "models/FQuimica.glb", //Cambiar el modelo
            detectionRange = 20f
        ),

    )
}