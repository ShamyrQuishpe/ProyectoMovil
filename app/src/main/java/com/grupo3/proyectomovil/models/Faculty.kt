package com.grupo3.proyectomovil.models

data class Faculty(
    val id: Int,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val detectionRange: Float = 20f
)
