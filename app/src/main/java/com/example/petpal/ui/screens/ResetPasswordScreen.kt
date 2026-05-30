package com.example.petpal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.petpal.R
import com.example.petpal.ui.theme.LocalPetPalColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petpal.ui.components.PetPalPrimaryButton
import com.example.petpal.ui.components.PetPalTextField
import com.example.petpal.ui.theme.SerifDisplayStyle
import com.example.petpal.ui.theme.Typography
import com.example.petpal.viewmodels.AuthState
import com.example.petpal.viewmodels.AuthViewModel

@Composable
fun ResetPasswordScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit
){
    val extraColors = LocalPetPalColors.current
    val colorScheme = MaterialTheme.colorScheme

    val focusManager = LocalFocusManager.current

    val currentAuthState by viewModel.authState.collectAsState()

    var email by remember { mutableStateOf("") }

    LaunchedEffect(currentAuthState) {
        if (currentAuthState is AuthState.Success){
            viewModel.resetState()
            println("RESET EMAIL SENT SUCCESSFULLY!")
            onNavigateToLogin()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(horizontal = 30.dp, vertical = 50.dp)
    ) {
        //Top action
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { onNavigateToLogin() }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back to login",
                tint = colorScheme.primary,
                modifier = Modifier.padding(end = 6.dp)
            )
            Text(
                text = "Back to login",
                style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        //Header
        Icon(
            painter = painterResource(id = R.drawable.ic_custom_paw),
            contentDescription = "PetPal Logo",
            tint = colorScheme.primary,
            modifier = Modifier.size(96.dp).padding(bottom = 24.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "ACCOUNT RECOVERY",
                style = Typography.labelMedium.copy(letterSpacing = 1.sp, fontWeight = FontWeight.Bold),
                color = colorScheme.primary
            )

            Text(
                text = "Reset your\npassword.",
                style = SerifDisplayStyle,
                color = colorScheme.onBackground
            )

            Text(
                text = "Enter your email and we'll send\nyou a secure reset link.",
                style = Typography.bodyMedium,
                color = extraColors.textSecondary
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        //Form section
        if (currentAuthState is AuthState.Error){
            Text(
                text = (currentAuthState as AuthState.Error).message,
                style = Typography.bodyMedium,
                color = colorScheme.error,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        PetPalTextField(
            label = "Email",
            placeholder = "",
            value = email,
            onValueChange = {email = it},
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        //Action button + message
        Column (
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            PetPalPrimaryButton(
                text = if (currentAuthState is AuthState.Loading) "Sending link..." else "Send reset link",
                onClick = {viewModel.resetPassword(email)}
            )

            Text(
                text = "We'll send you\na secure reset link.",
                style = Typography.bodyMedium,
                color = extraColors.textSecondary,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        //footer
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Remembered it? ",
                style = Typography.bodyMedium,
                color = extraColors.textSecondary
            )
            Text(
                text = "Log in",
                style = Typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = colorScheme.primary,
                modifier = Modifier.clickable { onNavigateToLogin() }
            )
        }
    }
}