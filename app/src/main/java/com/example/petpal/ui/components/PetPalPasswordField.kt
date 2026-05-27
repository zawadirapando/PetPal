package com.example.petpal.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.petpal.ui.theme.LocalPetPalColors
import com.example.petpal.ui.theme.PetPalTheme
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.petpal.ui.theme.Typography
import androidx.compose.ui.unit.dp

@Composable
fun PetPalPasswordField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit
){
    val extraColors = LocalPetPalColors.current
    var passwordVisible by remember { mutableStateOf(false) }

    Column (modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = Typography.labelMedium,
            color = extraColors.textLabel,
            modifier = Modifier.padding(8.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder, color = extraColors.textHint) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) {
                    android.R.drawable.ic_menu_view
                } else {
                    android.R.drawable.ic_secure
                }

                IconButton(onClick = {passwordVisible = !passwordVisible}) {
                    Icon(
                        painter = painterResource(id = image),
                        contentDescription = "Toggle Password Visibility",
                        tint = extraColors.textSecondary
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = extraColors.fieldFillFocused,
                unfocusedContainerColor = extraColors.fieldFill,
                focusedBorderColor = extraColors.fieldBorderFocused,
                unfocusedBorderColor = extraColors.fieldBorderInactive,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPetPalPasswordField(){
    PetPalTheme {
        PetPalPasswordField(
            label = "Password",
            placeholder = "••••••••",
            value = "",
            onValueChange = {}
        )
    }
}