package com.example.petpal.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.petpal.ui.theme.LocalPetPalColors
import com.example.petpal.ui.theme.PetPalTheme
import androidx.compose.ui.unit.dp
import com.example.petpal.ui.theme.Typography

@Composable
fun PetPlaOutlindButton(
    text: String,
    onClick: () -> Unit
){
    val extraColors = LocalPetPalColors.current
    val colorScheme = MaterialTheme.colorScheme

    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, extraColors.divider)
    ) {
        Text(
            text = text,
            style = Typography.labelLarge,
            color = colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPetPlaOutlindButton(){
    PetPalTheme {
        PetPlaOutlindButton(
            text = "I'm a sitter",
            onClick = {}
        )
    }
}