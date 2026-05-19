package com.example.nexolap.data.repository

import com.example.nexolap.R
import com.example.nexolap.modelo.OrdenadorDTO

/**
 * Implementación del repositorio para la gestión de datos de [OrdenadorDTO].
 *
 * Esta clase actúa como una fuente de datos estática que contiene una lista predefinida de
 * ordenadores de diversas marcas (Apple, Dell, HP, Lenovo, etc.) y proporciona métodos
 * para acceder a ellos a través de la interfaz [IOrdenadorRepo].
 *
 * @property ordenador Objeto compañero que mantiene la lista en memoria de los dispositivos disponibles.
 */
class OrdenadorRepo : IOrdenadorRepo {

    companion object {
        var ordenador = ArrayList(
            listOf(
                // Apple
                OrdenadorDTO(
                    id = "0",
                    nombre = "Apple MacBook Air 13\" (M2)",
                    imagenPrincipal = R.drawable.macbook_air_m2.toString(),
                ),
                OrdenadorDTO(
                    id = "1",
                    nombre = "Apple MacBook Pro 14\" (M3 Pro)",
                    imagenPrincipal = R.drawable.macbook_pro_14.toString(),
                ),
                OrdenadorDTO(
                    id = "2",
                    nombre = "Apple MacBook Pro 16\" (M3 Max)",
                    imagenPrincipal = R.drawable.macbook_pro_16.toString(),
                ),

                // Dell
                OrdenadorDTO(
                    id = "3",
                    nombre = "Dell XPS 13 (9320)",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),
                OrdenadorDTO(
                    id = "4",
                    nombre = "Dell XPS 15 (9530)",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),
                OrdenadorDTO(
                    id = "5",
                    nombre = "Dell Alienware m16 R1 (Gaming)",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),
                OrdenadorDTO(
                    id = "6",
                    nombre = "Dell Inspiron 15",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),

                // HP
                OrdenadorDTO(
                    id = "7",
                    nombre = "HP Spectre x360 14",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),
                OrdenadorDTO(
                    id = "8",
                    nombre = "HP Envy 16",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),
                OrdenadorDTO(
                    id = "9",
                    nombre = "HP Omen Transcend 16 (Gaming)",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),
                OrdenadorDTO(
                    id = "10",
                    nombre = "HP Pavilion Aero 13",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),

                // Lenovo
                OrdenadorDTO(
                    id = "11",
                    nombre = "Lenovo ThinkPad X1 Carbon Gen 11",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),
                OrdenadorDTO(
                    id = "12",
                    nombre = "Lenovo Yoga 9i (14\")",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),
                OrdenadorDTO(
                    id = "13",
                    nombre = "Lenovo Legion Pro 7i Gen 8 (Gaming)",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),
                OrdenadorDTO(
                    id = "14",
                    nombre = "Lenovo IdeaPad Slim 5",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),

                // Asus
                OrdenadorDTO(
                    id = "15",
                    nombre = "Asus ROG Zephyrus G14 (Gaming)",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),
                OrdenadorDTO(
                    id = "16",
                    nombre = "Asus Zenbook 14 OLED",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),
                OrdenadorDTO(
                    id = "17",
                    nombre = "Asus ProArt Studiobook 16",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),
                OrdenadorDTO(
                    id = "18",
                    nombre = "Asus TUF Gaming A15",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),

                // Acer
                OrdenadorDTO(
                    id = "19",
                    nombre = "Acer Swift Go 14",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),
                OrdenadorDTO(
                    id = "20",
                    nombre = "Acer Predator Helios 16 (Gaming)",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),
                OrdenadorDTO(
                    id = "21",
                    nombre = "Acer Aspire 5",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),

                // Microsoft
                OrdenadorDTO(
                    id = "22",
                    nombre = "Microsoft Surface Laptop 5",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),
                OrdenadorDTO(
                    id = "23",
                    nombre = "Microsoft Surface Laptop Studio 2",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),

                // Razer
                OrdenadorDTO(
                    id = "24",
                    nombre = "Razer Blade 15 (Gaming)",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),
                OrdenadorDTO(
                    id = "25",
                    nombre = "Razer Blade 18 (Gaming)",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),

                // MSI
                OrdenadorDTO(
                    id = "26",
                    nombre = "MSI Titan GT77 HX (Gaming)",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),
                OrdenadorDTO(
                    id = "27",
                    nombre = "MSI Katana 15 (Gaming)",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),
                OrdenadorDTO(
                    id = "28",
                    nombre = "MSI Prestige 14 Evo",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),

                // Samsung
                OrdenadorDTO(
                    id = "29",
                    nombre = "Samsung Galaxy Book3 Ultra",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                ),
                OrdenadorDTO(
                    id = "30",
                    nombre = "Samsung Galaxy Book3 Pro 360",
                    imagenPrincipal = R.drawable.ic_launcher_foreground.toString()
                )
            )
        )
    }


    override fun readAll(onSuccess: (List<OrdenadorDTO>) -> Unit, onError: () -> Unit) {
        onSuccess(ordenador)
    }

    override fun read(
        id: String,
        onSuccess: (ordenadorCreado: OrdenadorDTO?) -> Unit,
        onError: () -> Unit
    ) {
        onSuccess(ordenador.find { it.id == id })
    }

}

