package com.example.petpal.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.petpal.ui.theme.EyebrowStyle
import com.example.petpal.ui.theme.SerifDisplayStyle
import com.example.petpal.ui.theme.Typography

@Composable
fun PetPalHeader(
    eyebrow: String,
    title: String,
    modifier: Modifier = Modifier,
    trailingContent: (@Composable () -> Unit)? = null
){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column {
            Text(
                text = eyebrow.uppercase(),
                style = Typography.labelMedium.copy(letterSpacing = 1.sp),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = title,
                style = SerifDisplayStyle.copy(fontSize = 35.sp, fontWeight = FontWeight.Normal),
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        if (trailingContent != null) {
            Row (verticalAlignment = Alignment.CenterVertically){
                trailingContent()
            }
        }
    }

}