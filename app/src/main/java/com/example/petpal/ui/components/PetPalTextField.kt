package com.example.petpal.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.tooling.preview.Preview
import com.example.petpal.ui.theme.LocalPetPalColors
import com.example.petpal.ui.theme.PetPalTheme
import com.example.petpal.ui.theme.Typography
import androidx.compose.ui.unit.dp

@Composable
fun PetPalTextField (
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    val extraColors = LocalPetPalColors.current

    Column (modifier = modifier.fillMaxWidth() ) {
        Text(
            text = label,
            style = Typography.labelMedium,
            color = extraColors.textLabel,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder, color = extraColors.textHint) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
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
fun PreviewPetPalTextField() {
    PetPalTheme {
        PetPalTextField(
            label = "Email address",
            placeholder = "you@email.com",
            value = "",
            onValueChange = {}
        )
    }
}