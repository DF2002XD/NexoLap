package com.example.nexolap.vista.myComponents


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.ShapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable


/**
 * Un componente de TopAppBar personalizado que muestra un título y proporciona acciones opcionales de navegación y perfil.
 *
 * @param title El texto que se mostrará como título. Si [onBackClick] es nulo, el título se alinea al centro.
 * @param onBackClick Callback opcional para el icono de navegación hacia atrás. Si se proporciona, se muestra el icono de flecha.
 * @param onLogoutClick Callback opcional para la acción de "Cerrar Sesión" dentro del menú desplegable del perfil.
 * @param onProfileDetailsClick Callback opcional para la acción de "Detalles del Perfil" dentro del menú desplegable del perfil.
 * @param profileColor Color de fondo del avatar del perfil.
 * @param profileInitial Inicial del nombre del usuario para mostrar en el avatar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppTitle(
    title: String, onBackClick: (() -> Unit)? = null,
    onLogoutClick: (() -> Unit)? = null,
    onProfileDetailsClick: (() -> Unit)? = null,
    profileColor: Color = MaterialTheme.colorScheme.primary,
    profileInitial: String = "?"
) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            if (onBackClick == null) {
                Text(
                    text = title, color = Color.Black,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        },
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Regreso")
                }
            }
        },
        actions = {
            if (onLogoutClick != null || onProfileDetailsClick != null) {
                Box {
                    IconButton(onClick = { expanded = true }) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(profileColor),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = profileInitial.take(1).uppercase(),
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        if (onProfileDetailsClick != null) {
                            DropdownMenuItem(
                                text = { Text("Detalles del Perfil") },
                                onClick = {
                                    expanded = false
                                    onProfileDetailsClick()
                                }
                            )
                        }
                        if (onLogoutClick != null) {
                            DropdownMenuItem(
                                text = { Text("Cerrar Sesión") },
                                onClick = {
                                    expanded = false
                                    onLogoutClick()
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}


/**
 * Una barra de navegación inferior personalizada que utiliza [AnimatedNavigationBar] para transiciones animadas.
 *
 * Determina automáticamente el índice del elemento seleccionado basándose en la ruta actual
 * y proporciona botones para navegar a las secciones de inicio, búsqueda y perfil.
 *
 * @param currentRoute La ruta de navegación actual para determinar qué ícono debe estar activo.
 * @param onHomeClick Acción a ejecutar cuando se selecciona el ícono de inicio.
 * @param onSearchClick Acción a ejecutar cuando se selecciona el ícono de búsqueda.
 * @param onProfileClick Acción a ejecutar cuando se selecciona el ícono de perfil.
 */
@Composable
fun ButtomAppBarNav(
    currentRoute: String? = null,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onAddClick: () -> Unit = {},
    onProfileClick: () -> Unit
) {
    val selectedIndex = when {
        currentRoute?.startsWith("principal") == true -> 0
        currentRoute?.startsWith("busqueda") == true -> 1
        currentRoute?.startsWith("add_ordenador") == true -> 2
        currentRoute?.startsWith("perfil") == true -> 3
        else -> 0
    }

    AnimatedNavigationBar(
        modifier = Modifier
            .padding(8.dp)
            .height(64.dp),
        selectedIndex = selectedIndex,
        ballColor = MaterialTheme.colorScheme.primary,
        cornerRadius = ShapeCornerRadius(25f, 25f, 25f, 25f),
        ballAnimation = Parabolic(tween(300)),
        indentAnimation = Height(tween(300)),
        barColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        ColorButton(
            imageVector = Icons.Default.Home,
            isSelected = selectedIndex == 0,
            onClick = onHomeClick
        )
        ColorButton(
            imageVector = Icons.Default.Search,
            isSelected = selectedIndex == 1,
            onClick = onSearchClick
        )
        ColorButton(
            imageVector = Icons.Default.Add,
            isSelected = selectedIndex == 2,
            onClick = onAddClick
        )
        ColorButton(
            imageVector = Icons.Default.AccountCircle,
            isSelected = selectedIndex == 3,
            onClick = onProfileClick
        )
    }
}


@Composable
fun ColorButton(
    imageVector: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = Color.Gray
) {
    val alpha by animateFloatAsState(
        targetValue = if (isSelected) 1f else 0f,
        animationSpec = tween(500),
        label = "colorAlpha"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .noRippleClickable(onClick),
        contentAlignment = Alignment.Center
    ) {
        // Icono en estado no seleccionado (Gray)
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier
                .size(26.dp)
                .graphicsLayer { this.alpha = 1f - alpha },
            tint = unselectedColor
        )
        // Icono en estado seleccionado (Primary Color)
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier
                .size(26.dp)
                .graphicsLayer { this.alpha = alpha },
            tint = selectedColor
        )
    }
}

@Preview
@Composable
fun PreviewTopAppTitle() {
    TopAppTitle(title = "NexoLap", onLogoutClick = {}, onProfileDetailsClick = {})
}

@Preview
@Composable
fun PreviewTopAppTitleWithBack() {
    TopAppTitle(title = "NexoLap", onBackClick = {})

}

@Preview
@Composable
fun PreviewButtomAppBarNav() {
    ButtomAppBarNav(
        currentRoute = "principal/1",
        onHomeClick = {},
        onSearchClick = {},
        onProfileClick = {})
}
