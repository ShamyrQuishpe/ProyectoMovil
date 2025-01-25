package com.grupo3.proyectomovil.models

//clase de facultades
data class Faculty(
    val id: Int,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val modelPath: String,
    val detectionRange: Float = 20f
)
