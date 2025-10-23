package com.example.prj1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prj1.ui.theme.PRJ1Theme

class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PRJ1Theme {
                AppNavigator(authViewModel)
            }
        }
    }
}

@Composable
fun AppNavigator(viewModel: AuthViewModel) {
    val navController = rememberNavController()
    val user by viewModel.user.collectAsState()

    NavHost(navController = navController, startDestination = if (user == null) "login" else "home") {
        composable("login") {
            LoginScreen(viewModel = viewModel)
        }
        composable("home") {
            val currentUser by viewModel.user.collectAsState()
            HomeScreen(
                email = currentUser?.email,
                onLogoutClick = {
                    viewModel.signOut()
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true } // Clear back stack
                    }
                }
            )
        }
    }
}

@Composable
fun LoginScreen(viewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val error by viewModel.error.collectAsState()

    LoginScreenContent(
        email = email,
        onEmailChange = { email = it },
        password = password,
        onPasswordChange = { password = it },
        onLoginClick = { viewModel.signIn(email, password) },
        onRegisterClick = { viewModel.signUp(email, password) },
        error = error
    )
}

@Composable
fun LoginScreenContent(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    error: String?
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = email, onValueChange = onEmailChange, label = { Text("Email") })
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )

            if (error != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onLoginClick) {
                Text("Login")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onRegisterClick) {
                Text("Register")
            }
        }
    }
}

@Composable
fun HomeScreen(email: String?, onLogoutClick: () -> Unit) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome, ${email ?: ""}!")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onLogoutClick) {
                Text("Logout")
            }
        }
    }
}

@Preview(showBackground = true, name = "Home Screen", group = "Screens")
@Composable
fun HomeScreenPreview() {
    PRJ1Theme {
        HomeScreen(email = "user@example.com", onLogoutClick = {})
    }
}

@Preview(showBackground = true, name = "Login Screen - Empty", group = "Screens")
@Composable
fun LoginScreenEmptyPreview() {
    PRJ1Theme {
        LoginScreenContent(
            email = "", onEmailChange = {},
            password = "", onPasswordChange = {},
            onLoginClick = {}, onRegisterClick = {},
            error = null
        )
    }
}

@Preview(showBackground = true, name = "Login Screen - Error", group = "Screens")
@Composable
fun LoginScreenErrorPreview() {
    PRJ1Theme {
        LoginScreenContent(
            email = "user@example.com",
            onEmailChange = {},
            password = "password",
            onPasswordChange = {},
            onLoginClick = {},
            onRegisterClick = {},
            error = "Invalid email or password."
        )
    }
}
