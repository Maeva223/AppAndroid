package cl.tuorg.tuapp.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import cl.tuorg.tuapp.ui.theme.AppIotComposeTheme

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("¡Hola, usuario!", fontSize = 22.sp)
    }
}

// 👇 ¡AQUÍ ESTÁ! Esta es la línea que genera la vista previa
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AppIotComposeTheme {
        HomeScreen()
    }
}
