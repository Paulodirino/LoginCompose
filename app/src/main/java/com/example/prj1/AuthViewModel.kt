package com.example.prj1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    private val _user = MutableStateFlow(auth.currentUser)
    val user: StateFlow<com.google.firebase.auth.FirebaseUser?> = _user

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password).await()
                _user.value = auth.currentUser
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            }
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                _user.value = auth.currentUser
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            }
        }
    }

    fun signOut() {
        auth.signOut()
        _user.value = null
    }
}
