package com.grupo3.proyectomovil

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.*
import com.grupo3.proyectomovil.adapters.FacultyAdapter
import com.grupo3.proyectomovil.data.FacultyData
import com.grupo3.proyectomovil.databinding.ActivityLocationsBinding
import com.grupo3.proyectomovil.models.Faculty

class LocationsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLocationsBinding //binding para acceder a las vistas
    private lateinit var fusedLocationClient: FusedLocationProviderClient //cliente para la obtencion de ubicaciones
    private lateinit var facultyAdapter: FacultyAdapter //Adaptador para manejar la lista de facultades
    private var currentLocation: Location? = null //ubicacion actual del usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationsBinding.inflate(layoutInflater) // Inicializador del binding
        setContentView(binding.root) // Establece el contenido para la vista principal

        //configuraciones
        setupLocationClient()
        setupRecyclerView()
        setupSearch()

        // Solicitar permisos y ubicación inmediatamente
        checkPermissionsAndRequestLocation()
    }

    // Configura el cliente de ubicacion
    private fun setupLocationClient() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    // configura el RecyclerView para mostrar la lista de facutlades
    private fun setupRecyclerView() {
        // Inicializa el adaptador y el callback
        facultyAdapter = FacultyAdapter(FacultyData.faculties) { faculty ->
            navigateToAR(faculty) // Navegador entre facultades
        }
        binding.facultiesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@LocationsActivity) // Layout vertical
            adapter = facultyAdapter // Asocia el adaptador con el Recycler View
        }
    }

    // Navega a la actividad de Realidad Aumentada por facultad seleccionada
    private fun navigateToAR(faculty: Faculty) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(EXTRA_FACULTY_ID, faculty.id) // Pasa el ID de la facultad seleccionada
        startActivity(intent) // Inicia la nueva actividad
    }

    // Configuracion de la barra de busqueda
    private fun setupSearch() {
        binding.searchEditText.addTextChangedListener { text ->
            facultyAdapter.filter(text.toString())
        }
    }

    // Verifica los permisos de ubicacion y solicita la ubicacion actual
    private fun checkPermissionsAndRequestLocation() {
        when {
            // Permiso concedido = Actualizacion de ubicaciones
            checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED -> {
                startLocationUpdates()
                requestLastLocation()
            }
            // Pemriso denegado, solicita al usuario concederlo
            else -> {
                requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST
                )
            }
        }
    }

    // Inicia las actualizaciones periodicas de ubicacion
    private fun startLocationUpdates() {
        try {
            val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000) // Alta precision con intervalo de 1000 milisegundos
                .setMinUpdateDistanceMeters(1f) // Distancia entre actualizaciones
                .build()

            //Call back de solicitud de ubicaciones
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } catch (e: SecurityException) {
            e.printStackTrace() // Maneja posibles excepciones de seguridad
        }
    }

    // Solicitaa la ultima ubicacion conocida del dispositivo
    private fun requestLastLocation() {
        try {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    currentLocation = it // Almacena ubicacion actual
                    updateDistances() // Actualiza las distancias entre facultades
                }
            }
        } catch (e: SecurityException) {
            e.printStackTrace() // Maneja excepciones si faltan permisos
        }
    }

    // Call back que se ejecuta cuando hay actualziaciones de ubicacion
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            currentLocation = result.lastLocation
            updateDistances()
        }
    }

    // Calcula y actualiza la distancia entre la ubicacion actual y la de cada facultad
    private fun updateDistances() {
        currentLocation?.let { location ->
            FacultyData.faculties.forEach { faculty ->
                // Crea un objeto de ubicacion para la facultad
                val facultyLocation = Location("").apply {
                    latitude = faculty.latitude
                    longitude = faculty.longitude
                }
                // Calcula la distancia entre el usuario y la facultad
                val distance = location.distanceTo(facultyLocation)
                facultyAdapter.updateDistance(faculty.id, distance) // Actualiza la distancia en el adaptador
            }
        }
    }
    companion object {
        // Constante de identificacion de solicitud a permisos de ubicacion
        const val LOCATION_PERMISSION_REQUEST = 1000
        // Clave para pasar el ID de la facultad a la actividad AR
        const val EXTRA_FACULTY_ID = "extra_faculty_id"
    }
}