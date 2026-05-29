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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.petpal.ui.theme.LocalPetPalColors
import com.example.petpal.ui.theme.PetPalTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import com.example.petpal.R
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petpal.ui.components.PetPalTextField
import com.example.petpal.ui.components.PetPalPasswordField
import com.example.petpal.ui.components.PetPalPrimaryButton
import com.example.petpal.ui.components.PetPalOutlinedButton
import com.example.petpal.ui.theme.Typography
import com.example.petpal.ui.theme.SerifDisplayStyle
import com.example.petpal.viewmodels.AuthState
import com.example.petpal.viewmodels.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel = viewModel(),
    onNavigateToSignUp: () -> Unit,
    onNavigateToResetPassword: () -> Unit
){
    val extraColors = LocalPetPalColors.current
    val colorScheme = MaterialTheme.colorScheme

    val scrollState = rememberScrollState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val currentAuthState by viewModel.authState.collectAsState()

    LaunchedEffect(currentAuthState) {
        if (currentAuthState is AuthState.Success){
            viewModel.resetState()
            //TODO: navController.navigate("home")
            println("LOGIN SUCCESSFUL!")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .verticalScroll(scrollState)
            .padding(horizontal = 30.dp, vertical = 50.dp)
    ){
        //Top action
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.End
        ){
            Text(
                text = "Sign up",
                style = Typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = colorScheme.primary,
                modifier = Modifier.clickable { onNavigateToSignUp() }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        //Header
        Icon(
            painter = painterResource(id = R.drawable.ic_custom_paw),
            contentDescription = "PetPal logo",
            tint = colorScheme.primary,
            modifier = Modifier.size(96.dp).padding(bottom = 24.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Text(
                text = "WELCOME BACK",
                style = Typography.labelMedium.copy(letterSpacing = 1.sp, fontWeight = FontWeight.Bold),
                color = colorScheme.primary
            )

            Text(
                text = "Log in.",
                style = SerifDisplayStyle,
                color = colorScheme.onBackground,
            )

            Text(
                text = "Good to see you again",
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
            label = "Email address",
            placeholder = "",
            value = email,
            onValueChange = {email = it},
            modifier = Modifier.padding(bottom = 16.dp)
        )

        PetPalPasswordField(
            label = "Password",
            placeholder = "",
            value = password,
            onValueChange = {password = it}
        )

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.End
        ){
            Text(
                text = "Forgot password?",
                style = Typography.labelMedium,
                color = colorScheme.primary,
                modifier = Modifier.clickable { onNavigateToResetPassword() }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        //Action section
        PetPalPrimaryButton(
            text = if (currentAuthState is AuthState.Loading) "Logging in..." else "Log in",
            onClick = {viewModel.logIn(email, password)}
        )

        //or DIVIDER
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = extraColors.divider
            )
            Text(
                text = "or",
                style = Typography.bodyMedium,
                color = extraColors.textSecondary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = extraColors.divider
            )
        }

        PetPalOutlinedButton(
            text = "I'm a Sitter", //TODO: Connect this button to sitter UI
            onClick = {}
        )

        Spacer(modifier = Modifier.height(32.dp))

        //Footer
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
           Text(
               text = "New here? ",
               style = Typography.bodyMedium,
               color = extraColors.textSecondary
           )
            Text(
                text = "Create an account",
                style = Typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = colorScheme.primary,
                modifier = Modifier.clickable { onNavigateToSignUp() }
            )
        }
    }
}