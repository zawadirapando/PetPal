package com.example.petpal.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    data class Error(val message : String): AuthState()
}
class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    //LOG IN
    fun logIn(
        email: String,
        password: String
    ){
       if (email.isBlank() || password.isBlank()){
           _authState.value = AuthState.Error("Email and password cannot be empty")
           return
       }

        _authState.value = AuthState.Loading
        //TODO: Show spinner here

        auth.signInWithEmailAndPassword(email.trim(), password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    _authState.value = AuthState.Success
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?: "Login failed")
                }
            }
    }

    //SIGN UP
    fun signUp(
        email: String,
        password: String
    ){
        if (email.isBlank() || password.isBlank()){
            _authState.value = AuthState.Error("Email and password cannot be empty")
            return
        }

        _authState.value = AuthState.Loading
        //TODO: Show spinner here

        auth.signInWithEmailAndPassword(email.trim(), password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    _authState.value = AuthState.Success
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?: "Sign up failed")
                }
            }
    }

    //RESET PASSWORD
    fun resetPassword(
        email: String,
    ){
        if (email.isBlank()){
            _authState.value = AuthState.Error("Please enter your email")
            return
        }

        _authState.value = AuthState.Loading
        //TODO: Show spinner here

        auth.sendPasswordResetEmail(email.trim())
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    _authState.value = AuthState.Success
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?: "Failed to send reset email")
                }
            }
    }

    fun resetState(){
        _authState.value = AuthState.Idle
    }


}