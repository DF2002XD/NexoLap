package com.example.nexolap.Pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nexolap.myComponents.TopToolbar
import com.example.nexolap.myComponents.CardProfile

@Composable
fun ProfilePage() {
    Scaffold(
        topBar = { TopToolbar("Mi perfil") }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CardProfile(
                username = "Nombre Usuario",
                email = "hello@reallygreatsite.com",
                onUpdateEmail = {},
                onUpdatePassword = {}
            )
        }
    }
}


@Preview
@Composable
fun PreviewProfilepage(){
    ProfilePage()
}