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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petpal.R
import com.example.petpal.ui.components.PetPalPasswordField
import com.example.petpal.ui.components.PetPalPhoneField
import com.example.petpal.ui.components.PetPalPrimaryButton
import com.example.petpal.ui.components.PetPalTextField
import com.example.petpal.ui.theme.LocalPetPalColors
import com.example.petpal.ui.theme.SerifDisplayStyle
import com.example.petpal.ui.theme.Typography
import com.example.petpal.viewmodels.AuthState
import com.example.petpal.viewmodels.AuthViewModel

@Composable
fun SignUpScreen(
    viewModel: AuthViewModel = viewModel(),
    onNavigateToLogin: () -> Unit
){
    val extraColors = LocalPetPalColors.current
    val colorScheme = MaterialTheme.colorScheme

    val focusManager = LocalFocusManager.current

    val scrollState = rememberScrollState()

    val currentAuthState by viewModel.authState.collectAsState()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(currentAuthState) {
        if (currentAuthState is AuthState.Success){
            viewModel.resetState()
            //TODO: send to onboarding pages
            println("SIGN UP SUCCESSFUL!")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .systemBarsPadding()
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
                text = "Log in",
                style = Typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = colorScheme.primary,
                modifier = Modifier.clickable { onNavigateToLogin() }
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
                text = "NEW TO PETPAL",
                style = Typography.labelMedium.copy(letterSpacing = 1.sp, fontWeight = FontWeight.Bold),
                color = colorScheme.primary
            )

            Text(
                text = "Create your\naccount.",
                style = SerifDisplayStyle,
                color = colorScheme.onBackground
            )

            Text(
                text = "Find trusted sitters for\nevery pet you love.",
                style = Typography.bodyMedium,
                color = extraColors.textSecondary
            )
        }


        Spacer(modifier = Modifier.height(32.dp))

        //Form section
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (currentAuthState is AuthState.Error){
                Text(
                    text = (currentAuthState as AuthState.Error).message,
                    style = Typography.bodyMedium,
                    color = colorScheme.error,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PetPalTextField(
                    label = "First Name",
                    placeholder = "",
                    value = firstName,
                    onValueChange = {firstName = it},
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = {focusManager.moveFocus(FocusDirection.Next)}
                    ),
                    modifier = Modifier.weight(1f)
                )
                PetPalTextField(
                    label = "Last Name",
                    placeholder = "",
                    value = lastName,
                    onValueChange = {lastName = it},
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = {focusManager.moveFocus(FocusDirection.Next)}
                    ),
                    modifier = Modifier.weight(1f)
                )
            }

            PetPalTextField(
                label = "Email",
                placeholder = "",
                value = email,
                onValueChange = {email = it},
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {focusManager.moveFocus(FocusDirection.Next)}
                )
            )

            PetPalPhoneField(
                value = phone,
                onValueChange = {phone = it},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                )
            )

            PetPalPasswordField(
                label = "Password",
                placeholder = "",
                value = password,
                onValueChange = {password = it},
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        //Action button
        PetPalPrimaryButton(
            text = if (currentAuthState is AuthState.Loading) "Creating account..." else "Get started",
            onClick = {viewModel.signUp(email, password)}
        )

        Spacer(modifier = Modifier.height(32.dp))
        //Footer
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Already have an account? ",
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