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
        id = 0,
        nombre = "Apple MacBook Air 13\" (M2)",
        imagenPrincipal = R.drawable.macbook_air_m2,
    ),
    Ordenador(
        id = 1,
        nombre = "Apple MacBook Pro 14\" (M3 Pro)",
        imagenPrincipal = R.drawable.macbook_pro_14,
    ),
    Ordenador(
        id = 2,
        nombre = "Apple MacBook Pro 16\" (M3 Max)",
        imagenPrincipal = R.drawable.macbook_pro_16,
    ),

    // Dell
    Ordenador(id = 3, nombre = "Dell XPS 13 (9320)", imagenPrincipal = R.drawable.ic_launcher_foreground),
    Ordenador(id = 4, nombre = "Dell XPS 15 (9530)", imagenPrincipal = R.drawable.ic_launcher_foreground),
    Ordenador(id = 5, nombre = "Dell Alienware m16 R1 (Gaming)", imagenPrincipal = R.drawable.ic_launcher_foreground),
    Ordenador(id = 6, nombre = "Dell Inspiron 15", imagenPrincipal = R.drawable.ic_launcher_foreground),

    // HP
    Ordenador(id = 7, nombre = "HP Spectre x360 14", imagenPrincipal = R.drawable.ic_launcher_foreground),
    Ordenador(id = 8, nombre = "HP Envy 16", imagenPrincipal = R.drawable.ic_launcher_foreground),
    Ordenador(id = 9, nombre = "HP Omen Transcend 16 (Gaming)", imagenPrincipal = R.drawable.ic_launcher_foreground),
    Ordenador(id = 10, nombre = "HP Pavilion Aero 13", imagenPrincipal = R.drawable.ic_launcher_foreground),

    // Lenovo
    Ordenador(id = 11, nombre = "Lenovo ThinkPad X1 Carbon Gen 11", imagenPrincipal = R.drawable.ic_launcher_foreground),
    Ordenador(id = 12, nombre = "Lenovo Yoga 9i (14\")", imagenPrincipal = R.drawable.ic_launcher_foreground),
    Ordenador(id = 13, nombre = "Lenovo Legion Pro 7i Gen 8 (Gaming)", imagenPrincipal = R.drawable.ic_launcher_foreground),
    Ordenador(id = 14, nombre = "Lenovo IdeaPad Slim 5", imagenPrincipal = R.drawable.ic_launcher_foreground),

    // Asus
    Ordenador(id = 15, nombre = "Asus ROG Zephyrus G14 (Gaming)", imagenPrincipal = R.drawable.ic_launcher_foreground),
    Ordenador(id = 16, nombre = "Asus Zenbook 14 OLED", imagenPrincipal = R.drawable.ic_launcher_foreground),
    Ordenador(id = 17, nombre = "Asus ProArt Studiobook 16", imagenPrincipal = R.drawable.ic_launcher_foreground),
    Ordenador(id = 18, nombre = "Asus TUF Gaming A15", imagenPrincipal = R.drawable.ic_launcher_foreground),

    // Acer
    Ordenador(id = 19, nombre = "Acer Swift Go 14", imagenPrincipal = R.drawable.ic_launcher_foreground),
    Ordenador(id = 20, nombre = "Acer Predator Helios 16 (Gaming)", imagenPrincipal = R.drawable.ic_launcher_foreground),
    Ordenador(id = 21, nombre = "Acer Aspire 5", imagenPrincipal = R.drawable.ic_launcher_foreground),

    // Microsoft
    Ordenador(id = 22, nombre = "Microsoft Surface Laptop 5", imagenPrincipal = R.drawable.ic_launcher_foreground),
    Ordenador(id = 23, nombre = "Microsoft Surface Laptop Studio 2", imagenPrincipal = R.drawable.ic_launcher_foreground),

    // Razer
    Ordenador(id = 24, nombre = "Razer Blade 15 (Gaming)", imagenPrincipal = R.drawable.ic_launcher_foreground),
    Ordenador(id = 25, nombre = "Razer Blade 18 (Gaming)", imagenPrincipal = R.drawable.ic_launcher_foreground),

    // MSI
    Ordenador(id = 26, nombre = "MSI Titan GT77 HX (Gaming)", imagenPrincipal = R.drawable.ic_launcher_foreground),
    Ordenador(id = 27, nombre = "MSI Katana 15 (Gaming)", imagenPrincipal = R.drawable.ic_launcher_foreground),
    Ordenador(id = 28, nombre = "MSI Prestige 14 Evo", imagenPrincipal = R.drawable.ic_launcher_foreground),

    // Samsung
    Ordenador(id = 29, nombre = "Samsung Galaxy Book3 Ultra", imagenPrincipal = R.drawable.ic_launcher_foreground),
    Ordenador(id = 30, nombre = "Samsung Galaxy Book3 Pro 360", imagenPrincipal = R.drawable.ic_launcher_foreground)
)