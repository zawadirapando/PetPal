package com.example.petpal.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.petpal.ui.theme.LocalPetPalColors
import com.example.petpal.ui.theme.PetPalTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.petpal.ui.theme.Typography

@Composable
fun PetPalPrimaryButton(
    text: String,
    onClick: () -> Unit
){
    val extraColors = LocalPetPalColors.current

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = extraColors.blueFill,
            contentColor = Color.White
        )
    ) {
        Text(
            text = text,
            style = Typography.labelLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPetPalPrimaryButton() {
    PetPalTheme {
        PetPalPrimaryButton(
            text = "Sign in",
            onClick = {}
        )
    }
}