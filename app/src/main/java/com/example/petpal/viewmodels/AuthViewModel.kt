package com.example.petpal.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petpal.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    data class Error(val message : String): AuthState()
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    val isUserAuthenticated: Boolean
        get() = repository.isUserAuthenticated()

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

        viewModelScope.launch {
            val result = repository.logIn(email.trim(), password)

            result.fold(
                onSuccess = { _authState.value = AuthState.Success},
                onFailure = { exception ->
                    _authState.value = AuthState.Error(exception.message?: "Login failed")
                }
            )
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

        if (password.length < 8){
            _authState.value = AuthState.Error("Password must be at least 8 characters long")
            return
        }

        _authState.value = AuthState.Loading
        //TODO: Show spinner here

        viewModelScope.launch {
            val result = repository.signUp(email.trim(), password)

            result.fold(
                onSuccess = { _authState.value = AuthState.Success},
                onFailure = { exception ->
                    _authState.value = AuthState.Error(exception.message?: "Sign up failed")
                }
            )
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

        viewModelScope.launch {
            val result = repository.resetPassword(email.trim())

            result.fold(
                onSuccess = { _authState.value = AuthState.Success},
                onFailure = { exception ->
                    _authState.value = AuthState.Error(exception.message?: "Failed to send reset email")
                }
            )
        }
    }

    fun resetState(){
        _authState.value = AuthState.Idle
    }
}