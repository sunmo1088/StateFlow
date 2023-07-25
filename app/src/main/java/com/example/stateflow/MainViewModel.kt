package com.example.stateflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Empty)
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    fun login(username: String, password: String) = viewModelScope.launch {
        _loginUiState.value = LoginUiState.Loading
        delay(2000L)
        if (username == "android" && password == "topsecret") {
            _loginUiState.value = LoginUiState.Success
        } else {
            _loginUiState.value = LoginUiState.Error("Wrong credentials")
        }
    }

    sealed class LoginUiState {
        //because it does not need any parameter 'object' is being used.
        object Success : LoginUiState()
        data class Error(val message: String) : LoginUiState()
        object Loading : LoginUiState()
        object Empty : LoginUiState()
    }
}