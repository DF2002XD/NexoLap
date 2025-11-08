package com.example.nexolap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nexolap.Pages.InicioPage
import com.example.nexolap.Pages.PrincipalPage
import com.example.nexolap.Pages.PerfilPage
import com.example.nexolap.Pages.BusquedaPage
import com.example.nexolap.Pages.RegistroPage
import com.example.nexolap.ui.theme.NexoLapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NexoLapTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    InicioPage(modifier = Modifier.padding(innerPadding))
                    RegistroPage(modifier = Modifier.padding(innerPadding))
                    //PrincipalPage(modifier = Modifier.padding(innerPadding))
//                    BusquedaPage(modifier = Modifier.padding(innerPadding))
                    //PerfilPage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
