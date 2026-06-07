package com.example.petpal.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.petpal.ui.theme.LocalPetPalColors
import com.example.petpal.ui.theme.Typography

@Composable
fun ServiceCard(
    icon: ImageVector,
    title: String,
    subtitle: String,
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    titleColor: Color = MaterialTheme.colorScheme.onBackground,
    iconTint: Color = LocalPetPalColors.current.textSecondary,
    modifier: Modifier = Modifier
){
    val extraColors = LocalPetPalColors.current

    Card (
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ){
        Column (
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ){
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                style = Typography.titleMedium,
                color = titleColor
            )

            Text(
                text = subtitle,
                style = Typography.labelMedium,
                color = extraColors.textSecondary
            )
        }
    }
}