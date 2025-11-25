package cl.tuorg.tuapp.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cl.tuorg.tuapp.Data.Network.AuthApi
import cl.tuorg.tuapp.Data.Repository.AuthRepository
import cl.tuorg.tuapp.Nav.Route
import cl.tuorg.tuapp.Presentation.AuthViewModel
import cl.tuorg.tuapp.ui.theme.AppIotComposeTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun RegisterContent(
    name: String,
    lastName: String,
    email: String,
    phone: String,
    pass: String,
    onNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onPassChange: (String) -> Unit,
    onCreateClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Crear cuenta", fontSize = 22.sp)
        
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))
        
        OutlinedTextField(
            value = lastName,
            onValueChange = onLastNameChange,
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(Modifier.height(8.dp))
        
        OutlinedTextField(
            value = phone,
            onValueChange = onPhoneChange,
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = pass,
            onValueChange = onPassChange,
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onCreateClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear cuenta")
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar sesión")
        }
    }
}

@Composable
fun RegisterScreen(nav: NavController) {
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var pwd by remember { mutableStateOf("") }
    
    val context = LocalContext.current

    // --- LÓGICA DE RETROFIT Y VIEWMODEL (Paso 5 completado) ---
    val retrofit = remember {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/") 
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api = remember { retrofit.create(AuthApi::class.java) }
    val repository = remember { AuthRepository(api) }
    val viewModel = remember { AuthViewModel(repository) }

    RegisterContent(
        name = name,
        lastName = lastName,
        email = email,
        phone = phone,
        pass = pwd,
        onNameChange = { name = it },
        onLastNameChange = { lastName = it },
        onEmailChange = { email = it },
        onPhoneChange = { phone = it },
        onPassChange = { pwd = it },
        onCreateClick = { 
            // Aquí llamamos a la función register del ViewModel
            viewModel.register(
                name = "$name $lastName", // Concatenamos nombre y apellido
                email = email, 
                pass = pwd,
                onSuccess = {
                    Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    nav.navigate(Route.Login.path)
                },
                onFail = { errorMsg ->
                    Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
                }
            )
        },
        onLoginClick = {
            nav.navigate(Route.Login.path)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterContentPreview() {
    AppIotComposeTheme {
        RegisterContent(
            name = "Maria",
            lastName = "Valencia",
            email = "maria.valencia26@inacapmail.cl",
            phone = "+56985292221",
            pass = "password",
            onNameChange = {},
            onLastNameChange = {},
            onEmailChange = {},
            onPhoneChange = {},
            onPassChange = {},
            onCreateClick = {},
            onLoginClick = {}
        )
    }
}
