package com.example.nexolap.modelo

data class OrdenadorSpecsDTO( var id_Ordenador : OrdenadorDTO, var id_Especificacion : EspecificacionDTO)
data class OrdenadorSpecsDTORetroFit( var id_Ordenador : String, var id_Especificacion : String)
