package com.example.nexolap.Data

import com.example.nexolap.R

/**
 * Una lista predefinida de objetos [Ordenador] que sirve como fuente de datos de la aplicación.
 * Esta lista contiene varios modelos de portátiles de diferentes fabricantes como Apple, Dell, HP, etc.,
 * con algunos modelos que tienen especificaciones detalladas y otros que actúan como marcadores de posición.
 */
val ordenadores = listOf(
    // Apple
    Ordenador(
        id = 1,
        nombre = "Apple MacBook Air 13\" (M2)",
        imagenPrincipal = R.drawable.macbook_air_m2,
        especificaciones = listOf(
            Especificacion("Procesador", "Chip M2 de Apple con CPU de 8 núcleos y GPU de 8 núcleos"),
            Especificacion("Memoria RAM", "8 GB de memoria unificada (ampliable a 16 GB o 24 GB)"),
            Especificacion("Almacenamiento", "256 GB SSD (ampliable a 512 GB, 1 TB o 2 TB)"),
            Especificacion("Pantalla", "13,6 pulgadas Liquid Retina con True Tone, 500 nits"),
            Especificacion("Batería", "Hasta 18 horas de reproducción de vídeo"),
            Especificacion("Puertos", "Puerto de carga MagSafe 3, dos puertos Thunderbolt/USB 4"),
            Especificacion("Peso", "1,24 kg")
        )
    ),
    Ordenador(
        id = 2,
        nombre = "Apple MacBook Pro 14\" (M3 Pro)",
        imagenPrincipal = R.drawable.macbook_pro_14,
        especificaciones = listOf(
            Especificacion("Procesador", "Chip M3 Pro de Apple con CPU de hasta 12 núcleos y GPU de hasta 18 núcleos"),
            Especificacion("Memoria RAM", "18 GB de memoria unificada (ampliable a 36 GB)"),
            Especificacion("Almacenamiento", "512 GB SSD (ampliable a 1 TB, 2 TB o 4 TB)"),
            Especificacion("Pantalla", "14,2 pulgadas Liquid Retina XDR, ProMotion hasta 120 Hz"),
            Especificacion("Batería", "Hasta 18 horas de reproducción de vídeo"),
            Especificacion("Puertos", "Tres puertos Thunderbolt 4, puerto HDMI, ranura para tarjetas SDXC, MagSafe 3"),
            Especificacion("Peso", "1,61 kg")
        )
    ),
    Ordenador(
        id = 3,
        nombre = "Apple MacBook Pro 16\" (M3 Max)",
        imagenPrincipal = R.drawable.macbook_pro_16,
        especificaciones = listOf(
            Especificacion("Procesador", "Chip M3 Max de Apple con CPU de 16 núcleos y GPU de 40 núcleos"),
            Especificacion("Memoria RAM", "48 GB de memoria unificada (ampliable a 64 GB o 128 GB)"),
            Especificacion("Almacenamiento", "1 TB SSD (ampliable a 2 TB, 4 TB u 8 TB)"),
            Especificacion("Pantalla", "16,2 pulgadas Liquid Retina XDR, ProMotion hasta 120 Hz"),
            Especificacion("Batería", "Hasta 22 horas de reproducción de vídeo"),
            Especificacion("Puertos", "Tres puertos Thunderbolt 4, puerto HDMI, ranura para tarjetas SDXC, MagSafe 3"),
            Especificacion("Peso", "2,16 kg")
        )
    ),

    // Dell
    Ordenador(id = 4, nombre = "Dell XPS 13 (9320)", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),
    Ordenador(id = 5, nombre = "Dell XPS 15 (9530)", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),
    Ordenador(id = 6, nombre = "Dell Alienware m16 R1 (Gaming)", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),
    Ordenador(id = 7, nombre = "Dell Inspiron 15", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),

    // HP
    Ordenador(id = 8, nombre = "HP Spectre x360 14", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),
    Ordenador(id = 9, nombre = "HP Envy 16", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),
    Ordenador(id = 10, nombre = "HP Omen Transcend 16 (Gaming)", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),
    Ordenador(id = 11, nombre = "HP Pavilion Aero 13", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),

    // Lenovo
    Ordenador(id = 12, nombre = "Lenovo ThinkPad X1 Carbon Gen 11", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),
    Ordenador(id = 13, nombre = "Lenovo Yoga 9i (14\")", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),
    Ordenador(id = 14, nombre = "Lenovo Legion Pro 7i Gen 8 (Gaming)", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),
    Ordenador(id = 15, nombre = "Lenovo IdeaPad Slim 5", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),

    // Asus
    Ordenador(id = 16, nombre = "Asus ROG Zephyrus G14 (Gaming)", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),
    Ordenador(id = 17, nombre = "Asus Zenbook 14 OLED", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),
    Ordenador(id = 18, nombre = "Asus ProArt Studiobook 16", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),
    Ordenador(id = 19, nombre = "Asus TUF Gaming A15", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),

    // Acer
    Ordenador(id = 20, nombre = "Acer Swift Go 14", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),
    Ordenador(id = 21, nombre = "Acer Predator Helios 16 (Gaming)", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),
    Ordenador(id = 22, nombre = "Acer Aspire 5", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),

    // Microsoft
    Ordenador(id = 23, nombre = "Microsoft Surface Laptop 5", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),
    Ordenador(id = 24, nombre = "Microsoft Surface Laptop Studio 2", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),

    // Razer
    Ordenador(id = 25, nombre = "Razer Blade 15 (Gaming)", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),
    Ordenador(id = 26, nombre = "Razer Blade 18 (Gaming)", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),

    // MSI
    Ordenador(id = 27, nombre = "MSI Titan GT77 HX (Gaming)", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),
    Ordenador(id = 28, nombre = "MSI Katana 15 (Gaming)", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),
    Ordenador(id = 29, nombre = "MSI Prestige 14 Evo", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),

    // Samsung
    Ordenador(id = 30, nombre = "Samsung Galaxy Book3 Ultra", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList()),
    Ordenador(id = 31, nombre = "Samsung Galaxy Book3 Pro 360", imagenPrincipal = R.drawable.ic_launcher_foreground, especificaciones = emptyList())
)