package com.example.nexolap.vista.myComponents


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nexolap.R
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.ShapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppTitle(
    title: String, onBackClick: (() -> Unit)? = null,
    onLogoutClick: (() -> Unit)? = null,
    onProfileDetailsClick: (() -> Unit)? = null
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
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = "Perfil",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                        )
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


@Composable
fun ButtomAppBarNav(
    currentRoute: String? = null,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val selectedIndex = when {
        currentRoute?.startsWith("principal") == true -> 0
        currentRoute?.startsWith("busqueda") == true -> 1
        currentRoute?.startsWith("perfil") == true -> 2
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
            imageVector = Icons.Default.AccountCircle,
            isSelected = selectedIndex == 2,
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
